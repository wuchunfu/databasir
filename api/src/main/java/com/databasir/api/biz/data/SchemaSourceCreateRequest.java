package com.databasir.api.biz.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class SchemaSourceCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String schemaName;

    @NotBlank
    private String databaseType;

    @NotNull
    private ConnectionCreateRequest connection;

    @NotNull
    private SchemaSourceCreateRequest.SchemaMetaRuleCreateRequest schemaMetaRule;

    @Data
    public static class ConnectionCreateRequest {

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String url;

        private List<ConnectionPropertyValue> properties = new ArrayList<>();

    }

    @Data
    public static class SchemaMetaRuleCreateRequest {

        private List<String> ignoreTableNameRegexes = new ArrayList<>();

        private List<String> ignoreColumnNameRegexes = new ArrayList<>();

    }

}
