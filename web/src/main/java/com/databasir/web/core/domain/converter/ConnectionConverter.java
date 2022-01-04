package com.databasir.web.core.domain.converter;

import com.databasir.web.core.domain.data.ConnectionPropertyValue;
import com.databasir.web.core.domain.data.SchemaSourceCreateRequest;
import com.databasir.web.core.domain.data.SchemaSourceSimpleResponse;
import com.databasir.web.core.domain.data.SchemaSourceUpdateRequest;
import com.databasir.web.persist.tables.pojos.Connection;
import com.databasir.web.persist.tables.pojos.ConnectionProperty;
import com.databasir.web.persist.tables.pojos.SchemaSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ConnectionConverter {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "updateAt", ignore = true)
    })
    Connection of(SchemaSourceCreateRequest.ConnectionCreateRequest request, Integer schemaSourceId);

    @Mappings({
            @Mapping(target = "updateAt", ignore = true),
            @Mapping(target = "createAt", ignore = true)
    })
    Connection of(SchemaSourceUpdateRequest.ConnectionUpdateRequest request, Integer schemaSourceId);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true)
    })
    ConnectionProperty of(ConnectionPropertyValue propertyValues, Integer connectionId);

    default List<ConnectionProperty> of(List<ConnectionPropertyValue> propertyValues, Integer connectionId) {
        return propertyValues.stream().map(value -> of(value, connectionId)).collect(Collectors.toList());
    }

    SchemaSourceSimpleResponse toSimpleResponse(SchemaSource schemaSource);
}
