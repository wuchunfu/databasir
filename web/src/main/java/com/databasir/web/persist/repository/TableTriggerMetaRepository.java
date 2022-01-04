package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.TableTriggerMeta;
import com.databasir.web.persist.tables.records.TableTriggerMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.web.persist.Tables.TABLE_TRIGGER_META;

@Repository
public class TableTriggerMetaRepository extends BaseRepository<TableTriggerMetaRecord, TableTriggerMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableTriggerMetaRepository() {
        super(TABLE_TRIGGER_META, TableTriggerMeta.class);
    }

    public List<TableTriggerMeta> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_TRIGGER_META.fields()).from(TABLE_TRIGGER_META).where(TABLE_TRIGGER_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableTriggerMeta.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_TRIGGER_META).where(TABLE_TRIGGER_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
