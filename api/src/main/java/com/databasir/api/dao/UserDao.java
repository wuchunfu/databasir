package com.databasir.api.dao;

import com.databasir.dao.tables.pojos.UserPojo;
import com.databasir.dao.tables.records.UserRecord;
import lombok.Getter;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.databasir.dao.Tables.USER;


@Repository
public class UserDao extends BaseDao<UserRecord, UserPojo> {

    @Autowired
    @Getter
    private DSLContext dslContext;

    public UserDao() {
        super(USER, UserPojo.class);
    }

}
