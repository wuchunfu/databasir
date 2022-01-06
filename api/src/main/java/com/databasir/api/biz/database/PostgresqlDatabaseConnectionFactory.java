package com.databasir.api.biz.database;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

@Component
public class PostgresqlDatabaseConnectionFactory implements DatabaseConnectionFactory {

    @Override
    public boolean support(String databaseType) {
//        return DatabaseTypes.POSTGRESQL.equalsIgnoreCase(databaseType);
        return false;
    }

    @Override
    public Connection getConnection(String username, String password, String url, String schema, Properties properties) {
        return null;
    }
}
