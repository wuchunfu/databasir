package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.SchemaSourcePojo;
import com.databasir.dao.tables.records.SchemaSourceRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.dao.Tables.SCHEMA_SOURCE;


@Repository
public class SchemaSourceDao extends BaseDao<SchemaSourceRecord, SchemaSourcePojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaSourceDao() {
        super(SCHEMA_SOURCE, SchemaSourcePojo.class);
    }

    public int updateDeletedById(boolean b, Integer databaseId) {
        return dslContext
                .update(SCHEMA_SOURCE).set(SCHEMA_SOURCE.DELETED, b).where(SCHEMA_SOURCE.ID.eq(databaseId))
                .execute();
    }

    @Override
    public Optional<SchemaSourcePojo> selectOptionalById(Integer id) {
        return getDslContext()
                .select(SCHEMA_SOURCE.fields()).from(SCHEMA_SOURCE)
                .where(identity().eq(id).and(SCHEMA_SOURCE.DELETED.eq(false)))
                .fetchOptionalInto(SchemaSourcePojo.class);
    }

    @Override
    public boolean existsById(Integer id) {
        return getDslContext().fetchExists(table(), identity().eq(id).and(SCHEMA_SOURCE.DELETED.eq(false)));
    }
}
