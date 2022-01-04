package com.databasir.web.core.domain.service;

import com.databasir.core.Databasir;
import com.databasir.core.meta.data.DatabaseMeta;
import com.databasir.web.core.domain.converter.JsonConverter;
import com.databasir.web.core.domain.converter.MetaConverter;
import com.databasir.web.core.domain.converter.SchemaMetaHistoryConverter;
import com.databasir.web.core.domain.converter.MetaResponseConverter;
import com.databasir.web.core.domain.data.SchemaMetaResponse;
import com.databasir.web.core.domain.database.DatabaseConnections;
import com.databasir.web.persist.repository.*;
import com.databasir.web.persist.tables.pojos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaMetaService {

    private final SchemaSourceRepository schemaSourceRepository;

    private final ConnectionRepository connectionRepository;

    private final ConnectionPropertyRepository connectionPropertyRepository;

    private final SchemaMetaRepository schemaMetaRepository;

    private final TableMetaRepository tableMetaRepository;

    private final TableColumnMetaRepository tableColumnMetaRepository;

    private final TableIndexMetaRepository tableIndexMetaRepository;

    private final TableTriggerMetaRepository tableTriggerMetaRepository;

    private final SchemaMetaHistoryRepository schemaMetaHistoryRepository;

    private final MetaConverter metaConverter;

    private final SchemaMetaHistoryConverter schemaMetaHistoryConverter;

    private final MetaResponseConverter metaResponseConverter;

    private final JsonConverter jsonConverter;

    private final DatabaseConnections databaseConnections;

    @Transactional
    public void syncBySchemaSourceId(Integer schemaSourceId) {
        SchemaSource schemaSource = schemaSourceRepository.selectById(schemaSourceId);
        Connection connection = connectionRepository.selectBySchemaSourceId(schemaSourceId);
        List<ConnectionProperty> properties = connectionPropertyRepository.selectByConnectionId(connection.getId());
        String schemaName = schemaSource.getSchemaName();
        java.sql.Connection jdbcConnection =
                databaseConnections.create(schemaSource.getDatabaseType(), schemaName, connection, properties);

        Optional<DatabaseMeta> metaOptional = Databasir.of().get(jdbcConnection, schemaName);
        if (metaOptional.isPresent()) {
            Optional<SchemaMeta> schemaMetaOpt = schemaMetaRepository.selectOptionalBySchemaSourceId(schemaSourceId);
            Long version;
            if (schemaMetaOpt.isPresent()) {
                // save history
                SchemaMeta schemaMeta = schemaMetaOpt.get();
                version = schemaMeta.getVersion() + 1;
                Integer schemaMetaId = schemaMeta.getId();
                SchemaMetaResponse schemaMetaObj = getOne(schemaMetaId, null).orElse(null);
                SchemaMetaHistory history = schemaMetaHistoryConverter.of(schemaMetaObj, schemaSourceId, schemaMetaId, schemaMeta.getVersion());
                schemaMetaHistoryRepository.insertAndReturnId(history);
                log.info("save old meta info to history success");

                // delete old meta info
                tableMetaRepository.deleteBySchemaMetaId(schemaMetaId);
                tableColumnMetaRepository.deleteBySchemaMetaId(schemaMetaId);
                tableIndexMetaRepository.deleteBySchemaMetaId(schemaMetaId);
                tableTriggerMetaRepository.deleteBySchemaMetaId(schemaMetaId);
                log.info("delete old meta info success");
            } else {
                version = 1L;
            }

            // save new meta info
            DatabaseMeta meta = metaOptional.get();
            Integer schemaMetaId;
            if (schemaMetaOpt.isPresent()) {
                schemaMetaId = schemaMetaOpt.get().getId();
                Long newVersion = schemaMetaOpt.get().getVersion() + 1;
                schemaMetaRepository.update(metaConverter.toPojo(schemaSourceId, meta, schemaMetaId, newVersion));
            } else {
                schemaMetaId = schemaMetaRepository.insertAndReturnId(metaConverter.toPojo(schemaSourceId, meta, version));
            }
            meta.getTables().forEach(table -> {
                TableMeta tableMeta = metaConverter.toPojo(schemaMetaId, table);
                Integer tableMetaId = tableMetaRepository.insertAndReturnId(tableMeta);
                List<TableColumnMeta> tableColumnMetas = metaConverter.toColumnPojo(schemaMetaId, tableMetaId, table.getColumns());
                tableColumnMetaRepository.batchInsert(tableColumnMetas);
                List<TableIndexMeta> tableIndexMetas = metaConverter.toIndexPojo(schemaMetaId, tableMetaId, table.getIndexes());
                tableIndexMetaRepository.batchInsert(tableIndexMetas);
                List<TableTriggerMeta> tableTriggerMetas = metaConverter.toTriggerPojo(schemaMetaId, tableMetaId, table.getTriggers());
                tableTriggerMetaRepository.batchInsert(tableTriggerMetas);
            });
            log.info("save new meta info success");
        } else {
            log.warn("can not find database meta info with schema source id = " + schemaSourceId);
        }
    }

    public Optional<SchemaMetaResponse> getBySchemaSourceId(Integer schemaSourceId) {
        return schemaMetaRepository.selectOptionalBySchemaSourceId(schemaSourceId)
                .flatMap(schemaMeta -> getOne(schemaMeta.getId(), null));
    }

    public Optional<SchemaMetaResponse> getOne(Integer id, Long version) {
        if (version == null) {
            return schemaMetaRepository.selectOptionalById(id)
                    .map(schemaMeta -> {
                        List<TableMeta> tables = tableMetaRepository.selectBySchemaMetaId(id);
                        List<TableColumnMeta> columns = tableColumnMetaRepository.selectBySchemaMetaId(id);
                        List<TableIndexMeta> indexes = tableIndexMetaRepository.selectBySchemaMetaId(id);
                        List<TableTriggerMeta> triggers = tableTriggerMetaRepository.selectBySchemaMetaId(id);
                        Map<Integer, List<TableColumnMeta>> columnsGroupByTableMetaId = columns.stream()
                                .collect(Collectors.groupingBy(TableColumnMeta::getTableMetaId));
                        Map<Integer, List<TableIndexMeta>> indexesGroupByTableMetaId = indexes.stream()
                                .collect(Collectors.groupingBy(TableIndexMeta::getTableMetaId));
                        Map<Integer, List<TableTriggerMeta>> triggersGroupByTableMetaId = triggers.stream()
                                .collect(Collectors.groupingBy(TableTriggerMeta::getTableMetaId));
                        List<SchemaMetaResponse.TableMetaResponse> tableMetaResponseList = tables.stream()
                                .map(table -> {
                                    List<TableColumnMeta> subColumns = columnsGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    List<TableIndexMeta> subIndexes = indexesGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    List<TableTriggerMeta> subTriggers = triggersGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    return metaResponseConverter.of(table, subColumns, subIndexes, subTriggers);
                                })
                                .collect(Collectors.toList());
                        return metaResponseConverter.of(schemaMeta, tableMetaResponseList);
                    });
        } else {
            return schemaMetaHistoryRepository.selectOptionalBySchemaMetaIdAndVersion(id, version)
                    .map(obj -> jsonConverter.of(obj.getSchemaMetaObject()));
        }
    }
}
