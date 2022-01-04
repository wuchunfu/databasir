package com.databasir.web.core.domain.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class SchemaSourceUpdateRequest {

    @NotNull
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String schemaName;

    @NotBlank
    private String databaseType;

    @NotNull
    private ConnectionUpdateRequest connection;

    @NotNull
    private SchemaSourceUpdateRequest.SchemaMetaRuleUpdateRequest schemaMetaRule;

    @Data
    public static class ConnectionUpdateRequest {

        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String url;

        private List<ConnectionPropertyValue> properties = new ArrayList<>();
    }

    @Data
    public static class SchemaMetaRuleUpdateRequest {

        private List<String> ignoreTableNameRegexes = new ArrayList<>();

        private List<String> ignoreColumnNameRegexes = new ArrayList<>();

    }
}
