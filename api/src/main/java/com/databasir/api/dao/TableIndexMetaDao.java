package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.TableIndexMetaPojo;
import com.databasir.dao.tables.records.TableIndexMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.dao.Tables.TABLE_INDEX_META;


@Repository
public class TableIndexMetaDao extends BaseDao<TableIndexMetaRecord, TableIndexMetaPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableIndexMetaDao() {
        super(TABLE_INDEX_META, TableIndexMetaPojo.class);
    }

    public List<TableIndexMetaPojo> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_INDEX_META.fields()).from(TABLE_INDEX_META).where(TABLE_INDEX_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableIndexMetaPojo.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_INDEX_META).where(TABLE_INDEX_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
