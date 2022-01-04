package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.SchemaMetaHistory;
import com.databasir.web.persist.tables.records.SchemaMetaHistoryRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.web.persist.Tables.SCHEMA_META_HISTORY;

@Repository
public class SchemaMetaHistoryRepository extends BaseRepository<SchemaMetaHistoryRecord, SchemaMetaHistory> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaMetaHistoryRepository() {
        super(SCHEMA_META_HISTORY, SchemaMetaHistory.class);
    }

    public Optional<SchemaMetaHistory> selectOptionalBySchemaMetaIdAndVersion(Integer schemaMetaId, Long version) {
        return dslContext
                .selectFrom(SCHEMA_META_HISTORY).where(SCHEMA_META_HISTORY.SCHEMA_META_ID.eq(schemaMetaId).and(SCHEMA_META_HISTORY.VERSION.eq(version)))
                .fetchOptionalInto(SchemaMetaHistory.class);
    }
}
