package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.SchemaMetaHistoryPojo;
import com.databasir.dao.tables.records.SchemaMetaHistoryRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.dao.Tables.SCHEMA_META_HISTORY;


@Repository
public class SchemaMetaHistoryDao extends BaseDao<SchemaMetaHistoryRecord, SchemaMetaHistoryPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public SchemaMetaHistoryDao() {
        super(SCHEMA_META_HISTORY, SchemaMetaHistoryPojo.class);
    }

    public Optional<SchemaMetaHistoryPojo> selectOptionalBySchemaMetaIdAndVersion(Integer schemaMetaId, Long version) {
        return dslContext
                .selectFrom(SCHEMA_META_HISTORY).where(SCHEMA_META_HISTORY.SCHEMA_META_ID.eq(schemaMetaId).and(SCHEMA_META_HISTORY.VERSION.eq(version)))
                .fetchOptionalInto(SchemaMetaHistoryPojo.class);
    }

    public Page<SchemaMetaHistoryPojo> selectPageBySchemaMetaId(Pageable request, Integer schemaMetaId) {
        return super.selectByPage(request, SCHEMA_META_HISTORY.SCHEMA_META_ID.eq(schemaMetaId));
    }

}
