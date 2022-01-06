package com.databasir.api.biz.data;

import com.databasir.api.persist.Tables;
import lombok.Data;
import org.jooq.Condition;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

@Data
public class SchemaSourceListCondition {

    private String titleContains;

    private String schemaNameContains;

    private String databaseType;

    public Condition toCondition() {
        List<Condition> conditions = new ArrayList<>();
        if (titleContains != null) {
            Condition condition = Tables.SCHEMA_SOURCE.TITLE.contains(titleContains);
            conditions.add(condition);
        }
        if (schemaNameContains != null) {
            Condition condition = Tables.SCHEMA_SOURCE.SCHEMA_NAME.contains(schemaNameContains);
            conditions.add(condition);
        }
        if (databaseType != null) {
            Condition condition = Tables.SCHEMA_SOURCE.DATABASE_TYPE.eq(databaseType);
            conditions.add(condition);
        }
        conditions.add(Tables.SCHEMA_SOURCE.DELETED.eq(false));
        return conditions.stream().reduce(Condition::and).orElse(DSL.trueCondition());
    }
}
