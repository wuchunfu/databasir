package com.databasir.web.core.domain.database;

import java.sql.Connection;
import java.util.Properties;

public interface DatabaseConnectionFactory {

    boolean support(String databaseType);

    Connection getConnection(String username,
                             String password,
                             String url,
                             String schema,
                             Properties properties);
}
