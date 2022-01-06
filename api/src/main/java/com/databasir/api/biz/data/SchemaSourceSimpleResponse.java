package com.databasir.api.biz.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SchemaSourceSimpleResponse {

    private Integer id;

    private String title;

    private String description;

    private String schemaName;

    private String databaseType;

    private LocalDateTime createAt;

}
