package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.SchemaMetaPojo;
import com.databasir.dao.tables.records.SchemaMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.dao.Tables.SCHEMA_META;


@Repository
public class SchemaMetaDao extends BaseDao<SchemaMetaRecord, SchemaMetaPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaMetaDao() {
        super(SCHEMA_META, SchemaMetaPojo.class);
    }

    public Optional<SchemaMetaPojo> selectOptionalBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(SCHEMA_META.fields()).from(SCHEMA_META).where(SCHEMA_META.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(SchemaMetaPojo.class);
    }

    public void update(SchemaMetaPojo toPojo) {
        SchemaMetaRecord record = getDslContext().newRecord(SCHEMA_META, toPojo);
        record.changed(SCHEMA_META.ID, false);
        record.changed(SCHEMA_META.CREATE_AT, false);
        record.update();
    }
}
