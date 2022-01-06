package com.databasir.api.dao;

import com.databasir.api.persist.tables.pojos.TableIndexMeta;
import com.databasir.api.persist.tables.records.TableIndexMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.api.persist.Tables.TABLE_INDEX_META;

@Repository
public class TableIndexMetaDao extends BaseDao<TableIndexMetaRecord, TableIndexMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableIndexMetaDao() {
        super(TABLE_INDEX_META, TableIndexMeta.class);
    }

    public List<TableIndexMeta> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_INDEX_META.fields()).from(TABLE_INDEX_META).where(TABLE_INDEX_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableIndexMeta.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_INDEX_META).where(TABLE_INDEX_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
