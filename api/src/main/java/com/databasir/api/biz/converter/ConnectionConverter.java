package com.databasir.api.biz.converter;

import com.databasir.api.biz.data.ConnectionPropertyValue;
import com.databasir.api.biz.data.SchemaSourceCreateRequest;
import com.databasir.api.biz.data.SchemaSourceSimpleResponse;
import com.databasir.api.biz.data.SchemaSourceUpdateRequest;
import com.databasir.dao.tables.pojos.ConnectionPojo;
import com.databasir.dao.tables.pojos.ConnectionPropertyPojo;
import com.databasir.dao.tables.pojos.SchemaSourcePojo;
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
    ConnectionPojo of(SchemaSourceCreateRequest.ConnectionCreateRequest request, Integer schemaSourceId);

    @Mappings({
            @Mapping(target = "updateAt", ignore = true),
            @Mapping(target = "createAt", ignore = true)
    })
    ConnectionPojo of(SchemaSourceUpdateRequest.ConnectionUpdateRequest request, Integer schemaSourceId);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true)
    })
    ConnectionPropertyPojo of(ConnectionPropertyValue propertyValues, Integer connectionId);

    default List<ConnectionPropertyPojo> of(List<ConnectionPropertyValue> propertyValues, Integer connectionId) {
        return propertyValues.stream().map(value -> of(value, connectionId)).collect(Collectors.toList());
    }

    SchemaSourceSimpleResponse toSimpleResponse(SchemaSourcePojo schemaSource);
}
