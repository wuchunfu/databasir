package com.databasir.api.dao;

import com.databasir.dao.exception.DataNotExistsException;
import com.databasir.api.persist.tables.pojos.SchemaSourceMetaRule;
import com.databasir.api.persist.tables.records.SchemaSourceMetaRuleRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.api.persist.Tables.SCHEMA_SOURCE_META_RULE;

@Repository
public class SchemaSourceMetaRuleDao extends BaseDao<SchemaSourceMetaRuleRecord, SchemaSourceMetaRule> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaSourceMetaRuleDao() {
        super(SCHEMA_SOURCE_META_RULE, SchemaSourceMetaRule.class);
    }

    public Optional<SchemaSourceMetaRule> selectOptionalBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(SCHEMA_SOURCE_META_RULE.fields()).from(SCHEMA_SOURCE_META_RULE).where(SCHEMA_SOURCE_META_RULE.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(SchemaSourceMetaRule.class);
    }

    public SchemaSourceMetaRule selectBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(SCHEMA_SOURCE_META_RULE.fields()).from(SCHEMA_SOURCE_META_RULE).where(SCHEMA_SOURCE_META_RULE.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(SchemaSourceMetaRule.class)
                .orElseThrow(() -> new DataNotExistsException("data not exists in " + table().getName() + " with schemaSourceId = " + schemaSourceId));
    }

    public int updateBySchemaSourceId(SchemaSourceMetaRule rule) {
        com.databasir.api.persist.tables.SchemaSourceMetaRule table = SCHEMA_SOURCE_META_RULE;
        SchemaSourceMetaRuleRecord record = getDslContext().newRecord(table, rule);
        record.changed(table.ID, false);
        record.changed(table.SCHEMA_SOURCE_ID, false);
        return getDslContext()
                .update(table).set(record).where(table.SCHEMA_SOURCE_ID.eq(rule.getSchemaSourceId()))
                .execute();
    }
}
