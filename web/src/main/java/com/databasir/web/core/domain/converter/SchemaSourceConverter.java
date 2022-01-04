package com.databasir.web.core.domain.converter;

import com.databasir.web.core.domain.data.SchemaSourceCreateRequest;
import com.databasir.web.core.domain.data.SchemaSourceResponse;
import com.databasir.web.core.domain.data.SchemaSourceUpdateRequest;
import com.databasir.web.persist.tables.pojos.Connection;
import com.databasir.web.persist.tables.pojos.ConnectionProperty;
import com.databasir.web.persist.tables.pojos.SchemaSource;
import com.databasir.web.persist.tables.pojos.SchemaSourceMetaRule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = JsonConverter.class)
public interface SchemaSourceConverter {

    @Mapping(target = "id", source = "database.id")
    @Mapping(target = "createAt", source = "database.createAt")
    SchemaSourceResponse toResponse(SchemaSource database, SchemaSourceResponse.ConnectionResponse connection, SchemaSourceResponse.SchemaSourceMetaRuleResponse sourceMetaRule);

    SchemaSourceResponse.ConnectionResponse toResponse(Connection connection, List<ConnectionProperty> properties);

    @Mapping(target = "ignoreTableNameRegexes", source = "ignoreTableNameRegexArray")
    @Mapping(target = "ignoreColumnNameRegexes", source = "ignoreColumnNameRegexArray")
    SchemaSourceResponse.SchemaSourceMetaRuleResponse toResponse(SchemaSourceMetaRule rule);

    SchemaSource of(SchemaSourceCreateRequest request);

    SchemaSource of(SchemaSourceUpdateRequest request);
}
