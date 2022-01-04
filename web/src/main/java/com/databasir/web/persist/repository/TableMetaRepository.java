package com.databasir.web.persist.repository;

import com.databasir.web.persist.tables.pojos.TableMeta;
import com.databasir.web.persist.tables.records.TableMetaRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.web.persist.Tables.TABLE_META;

@Repository
public class TableMetaRepository extends BaseRepository<TableMetaRecord, TableMeta> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public TableMetaRepository() {
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
