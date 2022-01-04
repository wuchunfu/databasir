package com.databasir.web.core.domain.converter;

import com.databasir.core.meta.data.ColumnMeta;
import com.databasir.core.meta.data.IndexMeta;
import com.databasir.core.meta.data.TriggerMeta;
import com.databasir.web.persist.tables.pojos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = JsonConverter.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MetaConverter {

    @Mapping(target = "name", source = "meta.databaseName")
    SchemaMeta toPojo(Integer schemaSourceId, com.databasir.core.meta.data.DatabaseMeta meta, Long version);

    @Mapping(target = "name", source = "meta.databaseName")
    SchemaMeta toPojo(Integer schemaSourceId, com.databasir.core.meta.data.DatabaseMeta meta, Integer id, Long version);

    TableMeta toPojo(Integer schemaMetaId, com.databasir.core.meta.data.TableMeta meta);

    default List<TableColumnMeta> toColumnPojo(Integer schemaMetaId, Integer tableMetaId, List<ColumnMeta> metaList) {
        return metaList.stream()
                .map(meta -> toColumnPojo(schemaMetaId, tableMetaId, meta))
                .collect(Collectors.toList());
    }

    TableColumnMeta toColumnPojo(Integer schemaMetaId, Integer tableMetaId, ColumnMeta meta);

    default List<TableIndexMeta> toIndexPojo(Integer schemaMetaId, Integer tableMetaId, List<IndexMeta> metaList) {
        return metaList.stream()
                .map(meta -> toIndexPojo(schemaMetaId, tableMetaId, meta))
                .collect(Collectors.toList());
    }

    @Mapping(target = "isPrimary", source = "meta.isPrimaryKey")
    @Mapping(target = "isUnique", source = "meta.isUniqueKey")
    @Mapping(target = "columnNameArray", source = "meta.columnNames")
    TableIndexMeta toIndexPojo(Integer schemaMetaId, Integer tableMetaId, IndexMeta meta);

    default List<TableTriggerMeta> toTriggerPojo(Integer schemaMetaId, Integer tableMetaId, List<TriggerMeta> metaList) {
        return metaList.stream()
                .map(meta -> toTriggerPojo(schemaMetaId, tableMetaId, meta))
                .collect(Collectors.toList());
    }

    @Mapping(target = "triggerCreateAt", source = "meta.createAt")
    TableTriggerMeta toTriggerPojo(Integer databaseMetaId, Integer tableMetaId, TriggerMeta meta);

}
