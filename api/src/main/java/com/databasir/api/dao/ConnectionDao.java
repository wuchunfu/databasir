package com.databasir.api.dao;

import com.databasir.dao.exception.DataNotExistsException;
import com.databasir.dao.tables.pojos.ConnectionPojo;
import com.databasir.dao.tables.records.ConnectionRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.dao.Tables.CONNECTION;


@Repository
public class ConnectionDao extends BaseDao<ConnectionRecord, ConnectionPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public ConnectionDao() {
        super(CONNECTION, ConnectionPojo.class);
    }

    public Optional<ConnectionPojo> selectOptionalBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(CONNECTION.fields()).from(CONNECTION).where(CONNECTION.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(ConnectionPojo.class);
    }

    public ConnectionPojo selectBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(CONNECTION.fields()).from(CONNECTION).where(CONNECTION.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(ConnectionPojo.class)
                .orElseThrow(() -> new DataNotExistsException("data not exists in " + table().getName() + " with schemaSourceId = " + schemaSourceId));
    }

    public int updateBySchemaSourceId(ConnectionPojo connection) {
        ConnectionRecord record = getDslContext().newRecord(CONNECTION, connection);
        record.changed(CONNECTION.ID, false);
        record.changed(CONNECTION.SCHEMA_SOURCE_ID, false);
        return getDslContext()
                .update(CONNECTION).set(record).where(CONNECTION.SCHEMA_SOURCE_ID.eq(connection.getSchemaSourceId()))
                .execute();
    }
}
