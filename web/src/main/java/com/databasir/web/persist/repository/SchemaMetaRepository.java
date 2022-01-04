package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.SchemaMeta;
import com.databasir.web.persist.tables.records.SchemaMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.web.persist.Tables.SCHEMA_META;

@Repository
public class SchemaMetaRepository extends BaseRepository<SchemaMetaRecord, SchemaMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaMetaRepository() {
        super(SCHEMA_META, SchemaMeta.class);
    }

    public Optional<SchemaMeta> selectOptionalBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(SCHEMA_META.fields()).from(SCHEMA_META).where(SCHEMA_META.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(SchemaMeta.class);
    }

    public void update(SchemaMeta toPojo) {
        SchemaMetaRecord record = getDslContext().newRecord(SCHEMA_META, toPojo);
        record.changed(SCHEMA_META.ID, false);
        record.changed(SCHEMA_META.CREATE_AT, false);
        record.update();
    }
}
