package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.TableColumnMeta;
import com.databasir.web.persist.tables.records.TableColumnMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.web.persist.Tables.TABLE_COLUMN_META;

@Repository
public class TableColumnMetaRepository extends BaseRepository<TableColumnMetaRecord, TableColumnMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableColumnMetaRepository() {
        super(TABLE_COLUMN_META, TableColumnMeta.class);
    }

    public List<TableColumnMeta> selectBySchemaMetaId(Integer schemaMetaId) {
        return getDslContext()
                .select(TABLE_COLUMN_META.fields()).from(TABLE_COLUMN_META).where(TABLE_COLUMN_META.SCHEMA_META_ID.eq(schemaMetaId))
                .fetchInto(TableColumnMeta.class);
    }

    public void deleteBySchemaMetaId(Integer schemaMetaId) {
        getDslContext()
                .deleteFrom(TABLE_COLUMN_META).where(TABLE_COLUMN_META.SCHEMA_META_ID.eq(schemaMetaId))
                .execute();
    }
}
