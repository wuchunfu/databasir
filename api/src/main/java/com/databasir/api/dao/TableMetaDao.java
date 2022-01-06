package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.TableMetaPojo;
import com.databasir.dao.tables.records.TableMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.dao.Tables.TABLE_META;

@Repository
public class TableMetaDao extends BaseDao<TableMetaRecord, TableMetaPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableMetaDao() {
        super(TABLE_META, TableMetaPojo.class);
    }

    public List<TableMetaPojo> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_META.fields()).from(TABLE_META).where(TABLE_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableMetaPojo.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_META).where(TABLE_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
