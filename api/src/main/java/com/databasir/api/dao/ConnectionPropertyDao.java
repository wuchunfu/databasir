package com.databasir.api.dao;

import com.databasir.api.persist.tables.pojos.ConnectionProperty;
import com.databasir.api.persist.tables.records.ConnectionPropertyRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.databasir.api.persist.Tables.CONNECTION_PROPERTY;

@Repository
public class ConnectionPropertyDao extends BaseDao<ConnectionPropertyRecord, ConnectionProperty> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public ConnectionPropertyDao() {
        super(CONNECTION_PROPERTY, ConnectionProperty.class);
    }

    public int deleteByConnectionId(Integer connectionId) {
        return dslContext.deleteFrom(CONNECTION_PROPERTY).where("connection_id = ?", connectionId).execute();
    }

    public List<ConnectionProperty> selectByConnectionId(Integer id) {
        return dslContext
                .select(CONNECTION_PROPERTY.fields()).from(CONNECTION_PROPERTY).where(CONNECTION_PROPERTY.CONNECTION_ID.eq(id))
                .fetchInto(ConnectionProperty.class);
    }
}
