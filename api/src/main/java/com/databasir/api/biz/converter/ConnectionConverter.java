package com.databasir.api.biz.converter;

import com.databasir.api.biz.data.ConnectionPropertyValue;
import com.databasir.api.biz.data.SchemaSourceCreateRequest;
import com.databasir.api.biz.data.SchemaSourceSimpleResponse;
import com.databasir.api.biz.data.SchemaSourceUpdateRequest;
import com.databasir.api.persist.tables.pojos.Connection;
import com.databasir.api.persist.tables.pojos.ConnectionProperty;
import com.databasir.api.persist.tables.pojos.SchemaSource;
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
