package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.TableColumnMetaPojo;
import com.databasir.dao.tables.records.TableColumnMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.dao.Tables.TABLE_COLUMN_META;


@Repository
public class TableColumnMetaDao extends BaseDao<TableColumnMetaRecord, TableColumnMetaPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableColumnMetaDao() {
        super(TABLE_COLUMN_META, TableColumnMetaPojo.class);
    }

    public List<TableColumnMetaPojo> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_COLUMN_META.fields()).from(TABLE_COLUMN_META).where(TABLE_COLUMN_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableColumnMetaPojo.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_COLUMN_META).where(TABLE_COLUMN_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
