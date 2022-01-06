package com.databasir.api.dao;

import com.databasir.api.persist.tables.pojos.TableMeta;
import com.databasir.api.persist.tables.records.TableMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.api.persist.Tables.TABLE_META;

@Repository
public class TableMetaDao extends BaseDao<TableMetaRecord, TableMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableMetaDao() {
        super(TABLE_META, TableMeta.class);
    }

    public List<TableMeta> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_META.fields()).from(TABLE_META).where(TABLE_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableMeta.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_META).where(TABLE_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
