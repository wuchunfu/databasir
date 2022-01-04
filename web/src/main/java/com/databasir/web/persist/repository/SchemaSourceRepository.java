package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.SchemaSource;
import com.databasir.web.persist.tables.records.SchemaSourceRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.databasir.web.persist.Tables.SCHEMA_SOURCE;

@Repository
public class SchemaSourceRepository extends BaseRepository<SchemaSourceRecord, SchemaSource>{

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaSourceRepository() {
        super(SCHEMA_SOURCE, SchemaSource.class);
    }

    public int updateDeletedById(boolean b, Integer databaseId) {
        return dslContext
                .update(SCHEMA_SOURCE).set(SCHEMA_SOURCE.DELETED, b).where(SCHEMA_SOURCE.ID.eq(databaseId))
                .execute();
    }
}
