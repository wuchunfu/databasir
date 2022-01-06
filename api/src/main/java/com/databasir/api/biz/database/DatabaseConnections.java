package com.databasir.api.biz.database;

import com.databasir.api.biz.exception.WebDatabasirErrors;
import com.databasir.api.persist.tables.pojos.ConnectionProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class DatabaseConnections {

    private final List<DatabaseConnectionFactory> factories;

    public Connection create(String databaseType,
                             String schemaName,
                             com.databasir.api.persist.tables.pojos.Connection connection,
                             List<ConnectionProperty> properties) {
        String username = connection.getUsername();
        String password = connection.getPassword();
        String url = connection.getUrl();

        Properties info = new Properties();
        properties.forEach(prop -> info.put(prop.getKey(), prop.getValue()));
        return factories.stream()
                .filter(factory -> factory.support(databaseType))
                .findFirst()
                .orElseThrow(WebDatabasirErrors.NOT_SUPPORT_DATABASE_TYPE::exception)
                .getConnection(username, password, url, schemaName, info);
    }

}
