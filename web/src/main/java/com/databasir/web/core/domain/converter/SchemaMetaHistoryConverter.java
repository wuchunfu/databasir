package com.databasir.web.core.domain.converter;

import com.databasir.web.core.domain.data.SchemaMetaResponse;
import com.databasir.web.persist.tables.pojos.SchemaMetaHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = JsonConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchemaMetaHistoryConverter {

    @Mapping(target = "schemaMetaObject", source = "schemaMetaObject")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    SchemaMetaHistory of(SchemaMetaResponse schemaMetaObject,
                         Integer schemaSourceId,
                         Integer schemaMetaId,
                         Long version);
}
