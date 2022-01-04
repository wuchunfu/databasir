package com.databasir.web.core.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchemaMetaResponse {

    private Integer id;

    private String name;

    private String productName;

    private String productVersion;

    private Integer version;

    @Builder.Default
    private List<TableMetaResponse> tables = new ArrayList<>();

    private LocalDateTime createAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TableMetaResponse {

        private Integer id;

        private String name;

        private String type;

        private String comment;

        @Builder.Default
        private List<ColumnMetaResponse> columns = new ArrayList<>();

        @Builder.Default
        private List<IndexMetaResponse> indexes = new ArrayList<>();

        @Builder.Default
        private List<TriggerMetaResponse> triggers = new ArrayList<>();

        private LocalDateTime createAt;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class ColumnMetaResponse {
            private Integer id;

            private String name;

            private String type;

            private Integer size;

            private Integer decimalDigits;

            private String comment;

            private Boolean isNullable;

            private Boolean isAutoIncrement;

            private String defaultValue;

            private LocalDateTime createAt;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class IndexMetaResponse {

            private Integer id;

            private String name;

            private Boolean isPrimary;

            private Boolean isUnique;

            @Builder.Default
            private List<String> columnNames = new ArrayList<>();

            private LocalDateTime createAt;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class TriggerMetaResponse {

            private Integer id;

            private String name;

            private String timing;

            private String manipulation;

            private String statement;

            private String triggerCreateAt;

            private LocalDateTime createAt;
        }
    }
}
