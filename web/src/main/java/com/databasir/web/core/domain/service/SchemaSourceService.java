package com.databasir.web.core.domain.service;

import com.databasir.web.core.domain.converter.ConnectionConverter;
import com.databasir.web.core.domain.converter.SchemaSourceConverter;
import com.databasir.web.core.domain.converter.SchemaSourceMetaRuleConverter;
import com.databasir.web.core.domain.data.*;
import com.databasir.web.persist.repository.ConnectionPropertyRepository;
import com.databasir.web.persist.repository.ConnectionRepository;
import com.databasir.web.persist.repository.SchemaSourceMetaRuleRepository;
import com.databasir.web.persist.repository.SchemaSourceRepository;
import com.databasir.web.persist.tables.pojos.Connection;
import com.databasir.web.persist.tables.pojos.ConnectionProperty;
import com.databasir.web.persist.tables.pojos.SchemaSource;
import com.databasir.web.persist.tables.pojos.SchemaSourceMetaRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaSourceService {

    private final SchemaSourceRepository schemaSourceRepository;

    private final SchemaSourceMetaRuleRepository schemaSourceMetaRuleRepository;

    private final ConnectionRepository connectionRepository;

    private final ConnectionPropertyRepository connectionPropertyRepository;

    private final ConnectionConverter connectionConverter;

    private final SchemaSourceMetaRuleConverter databaseMetaRuleConverter;

    private final SchemaSourceConverter schemaSourceConverter;

    public SchemaSourceResponse getOne(Integer id) {
        return schemaSourceRepository.selectOptionalById(id)
                .map(schemaSource -> {
                    Connection connection = connectionRepository.selectBySchemaSourceId(id);
                    List<ConnectionProperty> properties = connectionPropertyRepository.selectByConnectionId(connection.getId());
                    SchemaSourceResponse.ConnectionResponse connectionResponse = schemaSourceConverter.toResponse(connection, properties);
                    SchemaSourceMetaRule rule = schemaSourceMetaRuleRepository.selectBySchemaSourceId(id);
                    SchemaSourceResponse.SchemaSourceMetaRuleResponse ruleResponse = schemaSourceConverter.toResponse(rule);
                    return schemaSourceConverter.toResponse(schemaSource, connectionResponse, ruleResponse);
                })
                .orElse(null);
    }

    @Transactional
    public Integer create(SchemaSourceCreateRequest request) {
        SchemaSource schemaSource = schemaSourceConverter.of(request);
        Integer schemaSourceId = schemaSourceRepository.insertAndReturnId(schemaSource);

        Connection connection = connectionConverter.of(request.getConnection(), schemaSourceId);
        Integer connectionId = connectionRepository.insertAndReturnId(connection);
        List<ConnectionPropertyValue> propertyValues = request.getConnection().getProperties();
        List<ConnectionProperty> properties = connectionConverter.of(propertyValues, connectionId);
        connectionPropertyRepository.batchInsert(properties);

        SchemaSourceMetaRule sourceMetaRule = databaseMetaRuleConverter.of(request.getSchemaMetaRule(), schemaSourceId);
        schemaSourceMetaRuleRepository.insertAndReturnId(sourceMetaRule);
        return schemaSourceId;
    }

    @Transactional
    public void update(SchemaSourceUpdateRequest request) {
        if (schemaSourceRepository.existsById(request.getId())) {
            // update connection
            Connection connection = connectionConverter.of(request.getConnection(), request.getId());
            connectionRepository.updateBySchemaSourceId(connection);

            // update connection property
            Integer connectionId = connectionRepository.selectBySchemaSourceId(request.getId()).getId();
            List<ConnectionPropertyValue> propertyValues = request.getConnection().getProperties();
            List<ConnectionProperty> properties = connectionConverter.of(propertyValues, connectionId);
            if (properties.isEmpty()) {
                connectionPropertyRepository.deleteByConnectionId(connectionId);
            } else {
                connectionPropertyRepository.deleteByConnectionId(connectionId);
                connectionPropertyRepository.batchInsert(properties);
            }

            SchemaSourceMetaRule sourceMetaRule = databaseMetaRuleConverter.of(request.getSchemaMetaRule(), request.getId());
            schemaSourceMetaRuleRepository.updateBySchemaSourceId(sourceMetaRule);

            SchemaSource schemaSource = schemaSourceConverter.of(request);
            schemaSourceRepository.updateById(schemaSource);
        } else {
            log.warn("update schema_source with not exists id {}", request.getId());
        }
    }

    public void delete(Integer databaseId) {
        schemaSourceRepository.updateDeletedById(true, databaseId);
    }

    public Page<SchemaSourceSimpleResponse> list(Pageable page, SchemaSourceListCondition condition) {
        Page<SchemaSource> pageData = schemaSourceRepository.selectByPage(page, condition.toCondition());
        return pageData.map(connectionConverter::toSimpleResponse);
    }
}
