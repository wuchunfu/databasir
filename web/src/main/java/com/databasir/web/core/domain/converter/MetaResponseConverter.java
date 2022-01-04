package com.databasir.web.core.domain.converter;

import com.databasir.web.core.domain.data.SchemaMetaResponse;
import com.databasir.web.persist.tables.pojos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = JsonConverter.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MetaResponseConverter {

    @Mapping(target = "columns", source = "columns")
    @Mapping(target = "indexes", source = "indexes")
    @Mapping(target = "triggers", source = "triggers")
    SchemaMetaResponse.TableMetaResponse of(TableMeta tableMeta,
                                            List<TableColumnMeta> columns,
                                            List<TableIndexMeta> indexes,
                                            List<TableTriggerMeta> triggers);

    @Mapping(target = "columnNames", source = "columnNameArray")
    SchemaMetaResponse.TableMetaResponse.IndexMetaResponse of(TableIndexMeta indexMeta);

    @Mapping(target = "id", source = "schemaMeta.id")
    @Mapping(target = "createAt", source = "schemaMeta.createAt")
    SchemaMetaResponse of(SchemaMeta schemaMeta,
                          List<SchemaMetaResponse.TableMetaResponse> tables);
}
