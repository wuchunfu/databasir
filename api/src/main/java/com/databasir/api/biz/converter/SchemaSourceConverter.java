package com.databasir.api.biz.converter;

import com.databasir.api.biz.data.SchemaSourceCreateRequest;
import com.databasir.api.biz.data.SchemaSourceResponse;
import com.databasir.api.biz.data.SchemaSourceUpdateRequest;
import com.databasir.dao.tables.pojos.ConnectionPojo;
import com.databasir.dao.tables.pojos.ConnectionPropertyPojo;
import com.databasir.dao.tables.pojos.SchemaSourceMetaRulePojo;
import com.databasir.dao.tables.pojos.SchemaSourcePojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = JsonConverter.class)
public interface SchemaSourceConverter {

    @Mapping(target = "id", source = "database.id")
    @Mapping(target = "createAt", source = "database.createAt")
    SchemaSourceResponse toResponse(SchemaSourcePojo database,
                                    SchemaSourceResponse.ConnectionResponse connection,
                                    SchemaSourceResponse.SchemaSourceMetaRuleResponse sourceMetaRule);

    SchemaSourceResponse.ConnectionResponse toResponse(ConnectionPojo connection, List<ConnectionPropertyPojo> properties);

    @Mapping(target = "ignoreTableNameRegexes", source = "ignoreTableNameRegexArray")
    @Mapping(target = "ignoreColumnNameRegexes", source = "ignoreColumnNameRegexArray")
    SchemaSourceResponse.SchemaSourceMetaRuleResponse toResponse(SchemaSourceMetaRulePojo rule);

    SchemaSourcePojo of(SchemaSourceCreateRequest request);

    SchemaSourcePojo of(SchemaSourceUpdateRequest request);
}
