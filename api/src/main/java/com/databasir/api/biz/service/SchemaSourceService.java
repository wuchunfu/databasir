package com.databasir.api.biz.service;

import com.databasir.api.biz.converter.ConnectionConverter;
import com.databasir.api.biz.converter.SchemaSourceConverter;
import com.databasir.api.biz.converter.SchemaSourceMetaRuleConverter;
import com.databasir.api.biz.data.*;
import com.databasir.api.biz.exception.WebDatabasirErrors;
import com.databasir.api.dao.ConnectionDao;
import com.databasir.api.dao.ConnectionPropertyDao;
import com.databasir.api.dao.SchemaSourceDao;
import com.databasir.api.dao.SchemaSourceMetaRuleDao;
import com.databasir.dao.tables.pojos.ConnectionPojo;
import com.databasir.dao.tables.pojos.ConnectionPropertyPojo;
import com.databasir.dao.tables.pojos.SchemaSourceMetaRulePojo;
import com.databasir.dao.tables.pojos.SchemaSourcePojo;
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

    private final SchemaSourceDao schemaSourceDao;

    private final SchemaSourceMetaRuleDao schemaSourceMetaRuleDao;

    private final ConnectionDao connectionDao;

    private final ConnectionPropertyDao connectionPropertyDao;

    private final ConnectionConverter connectionConverter;

    private final SchemaSourceMetaRuleConverter databaseMetaRuleConverter;

    private final SchemaSourceConverter schemaSourceConverter;

    public SchemaSourceResponse getOne(Integer id) {
        return schemaSourceDao.selectOptionalById(id)
                .map(schemaSource -> {
                    ConnectionPojo connection = connectionDao.selectBySchemaSourceId(id);
                    List<ConnectionPropertyPojo> properties = connectionPropertyDao.selectByConnectionId(connection.getId());
                    SchemaSourceResponse.ConnectionResponse connectionResponse = schemaSourceConverter.toResponse(connection, properties);
                    SchemaSourceMetaRulePojo rule = schemaSourceMetaRuleDao.selectBySchemaSourceId(id);
                    SchemaSourceResponse.SchemaSourceMetaRuleResponse ruleResponse = schemaSourceConverter.toResponse(rule);
                    return schemaSourceConverter.toResponse(schemaSource, connectionResponse, ruleResponse);
                })
                .orElseThrow(WebDatabasirErrors.SCHEMA_SOURCE_NOT_FOUND::exception);
    }

    @Transactional
    public Integer create(SchemaSourceCreateRequest request) {
        SchemaSourcePojo schemaSource = schemaSourceConverter.of(request);
        Integer schemaSourceId = schemaSourceDao.insertAndReturnId(schemaSource);

        ConnectionPojo connection = connectionConverter.of(request.getConnection(), schemaSourceId);
        Integer connectionId = connectionDao.insertAndReturnId(connection);
        List<ConnectionPropertyValue> propertyValues = request.getConnection().getProperties();
        List<ConnectionPropertyPojo> properties = connectionConverter.of(propertyValues, connectionId);
        connectionPropertyDao.batchInsert(properties);

        SchemaSourceMetaRulePojo sourceMetaRule = databaseMetaRuleConverter.of(request.getSchemaMetaRule(), schemaSourceId);
        schemaSourceMetaRuleDao.insertAndReturnId(sourceMetaRule);
        return schemaSourceId;
    }

    @Transactional
    public void update(SchemaSourceUpdateRequest request) {
        if (schemaSourceDao.existsById(request.getId())) {
            // update connection
            ConnectionPojo connection = connectionConverter.of(request.getConnection(), request.getId());
            connectionDao.updateBySchemaSourceId(connection);

            // update connection property
            Integer connectionId = connectionDao.selectBySchemaSourceId(request.getId()).getId();
            List<ConnectionPropertyValue> propertyValues = request.getConnection().getProperties();
            List<ConnectionPropertyPojo> properties = connectionConverter.of(propertyValues, connectionId);
            if (properties.isEmpty()) {
                connectionPropertyDao.deleteByConnectionId(connectionId);
            } else {
                connectionPropertyDao.deleteByConnectionId(connectionId);
                connectionPropertyDao.batchInsert(properties);
            }

            SchemaSourceMetaRulePojo sourceMetaRule = databaseMetaRuleConverter.of(request.getSchemaMetaRule(), request.getId());
            schemaSourceMetaRuleDao.updateBySchemaSourceId(sourceMetaRule);

            SchemaSourcePojo schemaSource = schemaSourceConverter.of(request);
            schemaSourceDao.updateById(schemaSource);
        } else {
            throw WebDatabasirErrors.SCHEMA_SOURCE_NOT_FOUND.exception();
        }
    }

    public void delete(Integer databaseId) {
        schemaSourceDao.updateDeletedById(true, databaseId);
    }

    public Page<SchemaSourceSimpleResponse> list(Pageable page, SchemaSourceListCondition condition) {
        Page<SchemaSourcePojo> pageData = schemaSourceDao.selectByPage(page, condition.toCondition());
        return pageData.map(connectionConverter::toSimpleResponse);
    }
}
