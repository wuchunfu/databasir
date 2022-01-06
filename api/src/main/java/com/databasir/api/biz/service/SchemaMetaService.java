package com.databasir.api.biz.service;

import com.databasir.api.biz.converter.JsonConverter;
import com.databasir.api.biz.converter.MetaConverter;
import com.databasir.api.biz.converter.MetaResponseConverter;
import com.databasir.api.biz.converter.SchemaMetaHistoryConverter;
import com.databasir.api.biz.data.SchemaMetaResponse;
import com.databasir.api.biz.data.SchemaMetaVersionResponse;
import com.databasir.api.biz.database.DatabaseConnections;
import com.databasir.api.biz.exception.WebDatabasirErrors;
import com.databasir.api.dao.*;
import com.databasir.core.Databasir;
import com.databasir.core.meta.data.DatabaseMeta;
import com.databasir.dao.tables.pojos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchemaMetaService {

    private final SchemaSourceDao schemaSourceDao;

    private final ConnectionDao connectionDao;

    private final ConnectionPropertyDao connectionPropertyDao;

    private final SchemaMetaDao schemaMetaDao;

    private final TableMetaDao tableMetaDao;

    private final TableColumnMetaDao tableColumnMetaDao;

    private final TableIndexMetaDao tableIndexMetaDao;

    private final TableTriggerMetaDao tableTriggerMetaDao;

    private final SchemaMetaHistoryDao schemaMetaHistoryDao;

    private final MetaConverter metaConverter;

    private final SchemaMetaHistoryConverter schemaMetaHistoryConverter;

    private final MetaResponseConverter metaResponseConverter;

    private final JsonConverter jsonConverter;

    private final DatabaseConnections databaseConnections;

    @Transactional
    public void syncBySchemaSourceId(Integer schemaSourceId) {
        SchemaSourcePojo schemaSource = schemaSourceDao.selectOptionalById(schemaSourceId)
                .orElseThrow(WebDatabasirErrors.SCHEMA_SOURCE_NOT_FOUND::exception);
        ConnectionPojo connection = connectionDao.selectBySchemaSourceId(schemaSourceId);
        List<ConnectionPropertyPojo> properties = connectionPropertyDao.selectByConnectionId(connection.getId());
        String schemaName = schemaSource.getSchemaName();
        Connection jdbcConnection =
                databaseConnections.create(schemaSource.getDatabaseType(), schemaName, connection, properties);

        DatabaseMeta meta = Databasir.of()
                .get(jdbcConnection, schemaName)
                .orElseThrow(WebDatabasirErrors.DATABASE_META_NOT_FOUND::exception);
        Optional<SchemaMetaPojo> schemaMetaOpt = schemaMetaDao.selectOptionalBySchemaSourceId(schemaSourceId);
        Long version;
        if (schemaMetaOpt.isPresent()) {
            // save history
            SchemaMetaPojo schemaMeta = schemaMetaOpt.get();
            version = schemaMeta.getVersion() + 1;
            Integer schemaMetaId = schemaMeta.getId();
            SchemaMetaResponse schemaMetaObj = getOne(schemaMetaId, null).orElse(null);
            SchemaMetaHistoryPojo history = schemaMetaHistoryConverter.of(schemaMetaObj, schemaSourceId, schemaMetaId, schemaMeta.getVersion());
            schemaMetaHistoryDao.insertAndReturnId(history);
            log.info("save old meta info to history success");

            // delete old meta info
            tableMetaDao.deleteBySchemaMetaId(schemaMetaId);
            tableColumnMetaDao.deleteBySchemaMetaId(schemaMetaId);
            tableIndexMetaDao.deleteBySchemaMetaId(schemaMetaId);
            tableTriggerMetaDao.deleteBySchemaMetaId(schemaMetaId);
            log.info("delete old meta info success");
        } else {
            version = 1L;
        }

        // save new meta info
        Integer schemaMetaId;
        if (schemaMetaOpt.isPresent()) {
            schemaMetaId = schemaMetaOpt.get().getId();
            Long newVersion = schemaMetaOpt.get().getVersion() + 1;
            schemaMetaDao.update(metaConverter.toPojo(schemaSourceId, meta, schemaMetaId, newVersion));
        } else {
            schemaMetaId = schemaMetaDao.insertAndReturnId(metaConverter.toPojo(schemaSourceId, meta, version));
        }
        meta.getTables().forEach(table -> {
            TableMetaPojo tableMeta = metaConverter.toPojo(schemaMetaId, table);
            Integer tableMetaId = tableMetaDao.insertAndReturnId(tableMeta);
            List<TableColumnMetaPojo> tableColumnMetas = metaConverter.toColumnPojo(schemaMetaId, tableMetaId, table.getColumns());
            tableColumnMetaDao.batchInsert(tableColumnMetas);
            List<TableIndexMetaPojo> tableIndexMetas = metaConverter.toIndexPojo(schemaMetaId, tableMetaId, table.getIndexes());
            tableIndexMetaDao.batchInsert(tableIndexMetas);
            List<TableTriggerMetaPojo> tableTriggerMetas = metaConverter.toTriggerPojo(schemaMetaId, tableMetaId, table.getTriggers());
            tableTriggerMetaDao.batchInsert(tableTriggerMetas);
        });
        log.info("save new meta info success");
    }

    public Optional<SchemaMetaResponse> getBySchemaSourceId(Integer schemaSourceId) {
        return schemaMetaDao.selectOptionalBySchemaSourceId(schemaSourceId)
                .flatMap(schemaMeta -> getOne(schemaMeta.getId(), null));
    }

    public Optional<SchemaMetaResponse> getOne(Integer id, Long version) {
        if (version == null) {
            return schemaMetaDao.selectOptionalById(id)
                    .map(schemaMeta -> {
                        List<TableMetaPojo> tables = tableMetaDao.selectBySchemaMetaId(id);
                        List<TableColumnMetaPojo> columns = tableColumnMetaDao.selectBySchemaMetaId(id);
                        List<TableIndexMetaPojo> indexes = tableIndexMetaDao.selectBySchemaMetaId(id);
                        List<TableTriggerMetaPojo> triggers = tableTriggerMetaDao.selectBySchemaMetaId(id);
                        Map<Integer, List<TableColumnMetaPojo>> columnsGroupByTableMetaId = columns.stream()
                                .collect(Collectors.groupingBy(TableColumnMetaPojo::getTableMetaId));
                        Map<Integer, List<TableIndexMetaPojo>> indexesGroupByTableMetaId = indexes.stream()
                                .collect(Collectors.groupingBy(TableIndexMetaPojo::getTableMetaId));
                        Map<Integer, List<TableTriggerMetaPojo>> triggersGroupByTableMetaId = triggers.stream()
                                .collect(Collectors.groupingBy(TableTriggerMetaPojo::getTableMetaId));
                        List<SchemaMetaResponse.TableMetaResponse> tableMetaResponseList = tables.stream()
                                .map(table -> {
                                    List<TableColumnMetaPojo> subColumns = columnsGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    List<TableIndexMetaPojo> subIndexes = indexesGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    List<TableTriggerMetaPojo> subTriggers = triggersGroupByTableMetaId.getOrDefault(table.getId(), Collections.emptyList());
                                    return metaResponseConverter.of(table, subColumns, subIndexes, subTriggers);
                                })
                                .collect(Collectors.toList());
                        return metaResponseConverter.of(schemaMeta, tableMetaResponseList);
                    });
        } else {
            return schemaMetaHistoryDao.selectOptionalBySchemaMetaIdAndVersion(id, version)
                    .map(obj -> jsonConverter.of(obj.getSchemaMetaObject()));
        }
    }

    public Page<SchemaMetaVersionResponse> getVersionsBySchemaSourceId(Integer schemaSourceId, Pageable page) {
        return schemaMetaDao.selectOptionalBySchemaSourceId(schemaSourceId)
                .map(schemaMeta -> schemaMetaHistoryDao.selectPageBySchemaMetaId(page, schemaMeta.getId())
                        .map(history -> SchemaMetaVersionResponse.builder()
                                .version(history.getVersion())
                                .createAt(history.getCreateAt())
                                .build()))
                .orElseGet(Page::empty);
    }
}
