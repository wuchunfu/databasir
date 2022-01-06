package com.databasir.api.biz.converter;

import com.databasir.api.biz.data.SchemaSourceCreateRequest;
import com.databasir.api.biz.data.SchemaSourceUpdateRequest;
import com.databasir.api.persist.tables.pojos.SchemaSourceMetaRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = JsonConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchemaSourceMetaRuleConverter {

    @Mapping(target = "ignoreTableNameRegexArray", source = "request.ignoreTableNameRegexes")
    @Mapping(target = "ignoreColumnNameRegexArray", source = "request.ignoreColumnNameRegexes")
    SchemaSourceMetaRule of(SchemaSourceCreateRequest.SchemaMetaRuleCreateRequest request, Integer schemaSourceId);

    @Mapping(target = "ignoreTableNameRegexArray", source = "request.ignoreTableNameRegexes")
    @Mapping(target = "ignoreColumnNameRegexArray", source = "request.ignoreColumnNameRegexes")
    SchemaSourceMetaRule of(SchemaSourceUpdateRequest.SchemaMetaRuleUpdateRequest request, Integer schemaSourceId);

}
