package com.databasir.api.dao;

import com.databasir.api.persist.tables.pojos.SchemaSource;
import com.databasir.api.persist.tables.records.SchemaSourceRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.api.persist.Tables.SCHEMA_SOURCE;

@Repository
public class SchemaSourceDao extends BaseDao<SchemaSourceRecord, SchemaSource> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaSourceDao() {
        super(SCHEMA_SOURCE, SchemaSource.class);
    }

    public int updateDeletedById(boolean b, Integer databaseId) {
        return dslContext
                .update(SCHEMA_SOURCE).set(SCHEMA_SOURCE.DELETED, b).where(SCHEMA_SOURCE.ID.eq(databaseId))
                .execute();
    }

    @Override
    public Optional<SchemaSource> selectOptionalById(Integer id) {
        return getDslContext()
                .select(SCHEMA_SOURCE.fields()).from(SCHEMA_SOURCE)
                .where(identity().eq(id).and(SCHEMA_SOURCE.DELETED.eq(false)))
                .fetchOptionalInto(SchemaSource.class);
    }

    @Override
    public boolean existsById(Integer id) {
        return getDslContext().fetchExists(table(), identity().eq(id).and(SCHEMA_SOURCE.DELETED.eq(false)));
    }
}
