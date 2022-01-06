package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.TableTriggerMetaPojo;
import com.databasir.dao.tables.records.TableTriggerMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.dao.Tables.TABLE_TRIGGER_META;

@Repository
public class TableTriggerMetaDao extends BaseDao<TableTriggerMetaRecord, TableTriggerMetaPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableTriggerMetaDao() {
        super(TABLE_TRIGGER_META, TableTriggerMetaPojo.class);
    }

    public List<TableTriggerMetaPojo> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_TRIGGER_META.fields()).from(TABLE_TRIGGER_META).where(TABLE_TRIGGER_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableTriggerMetaPojo.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_TRIGGER_META).where(TABLE_TRIGGER_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
