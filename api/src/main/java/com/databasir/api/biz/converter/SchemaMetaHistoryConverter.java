package com.databasir.api.biz.converter;

import com.databasir.api.biz.data.SchemaMetaResponse;
import com.databasir.dao.tables.pojos.SchemaMetaHistoryPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = JsonConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchemaMetaHistoryConverter {

    @Mapping(target = "schemaMetaObject", source = "schemaMetaObject")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    SchemaMetaHistoryPojo of(SchemaMetaResponse schemaMetaObject,
                             Integer schemaSourceId,
                             Integer schemaMetaId,
                             Long version);
}
