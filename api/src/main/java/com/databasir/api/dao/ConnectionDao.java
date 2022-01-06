package com.databasir.api.dao;

import com.databasir.dao.exception.DataNotExistsException;
import com.databasir.api.persist.tables.pojos.Connection;
import com.databasir.api.persist.tables.records.ConnectionRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.databasir.api.persist.Tables.CONNECTION;

@Repository
public class ConnectionDao extends BaseDao<ConnectionRecord, Connection> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public ConnectionDao() {
        super(CONNECTION, Connection.class);
    }

    public Optional<Connection> selectOptionalBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(CONNECTION.fields()).from(CONNECTION).where(CONNECTION.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(Connection.class);
    }

    public Connection selectBySchemaSourceId(Integer schemaSourceId) {
        return getDslContext()
                .select(CONNECTION.fields()).from(CONNECTION).where(CONNECTION.SCHEMA_SOURCE_ID.eq(schemaSourceId))
                .fetchOptionalInto(Connection.class)
                .orElseThrow(() -> new DataNotExistsException("data not exists in " + table().getName() + " with schemaSourceId = " + schemaSourceId));
    }

    public int updateBySchemaSourceId(Connection connection) {
        ConnectionRecord record = getDslContext().newRecord(CONNECTION, connection);
        record.changed(CONNECTION.ID, false);
        record.changed(CONNECTION.SCHEMA_SOURCE_ID, false);
        return getDslContext()
                .update(CONNECTION).set(record).where(CONNECTION.SCHEMA_SOURCE_ID.eq(connection.getSchemaSourceId()))
                .execute();
    }
}
