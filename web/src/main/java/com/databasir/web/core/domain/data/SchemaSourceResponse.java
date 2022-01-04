package com.databasir.web.core.domain.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SchemaSourceResponse {

    private Integer id;

    private String title;

    private String description;

    private String schemaName;

    private String databaseType;

    private ConnectionResponse connection;

    private SchemaSourceMetaRuleResponse sourceMetaRule;

    private LocalDateTime createAt;

    @Data
    public static class ConnectionResponse {

        private Integer id;

        private String username;

        private String password;

        private String url;

        private List<ConnectionPropertyValue> properties = new ArrayList<>();

        private LocalDateTime updateAt;

        private LocalDateTime createAt;

    }

    @Data
    public static class SchemaSourceMetaRuleResponse {

        private Integer id;

        private List<String> ignoreTableNameRegexes = new ArrayList<>();

        private List<String> ignoreColumnNameRegexes = new ArrayList<>();

        private LocalDateTime createAt;
    }
}
