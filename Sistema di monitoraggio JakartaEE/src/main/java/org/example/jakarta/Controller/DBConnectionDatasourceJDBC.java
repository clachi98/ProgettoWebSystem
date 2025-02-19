package org.example.jakarta.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnectionDatasourceJDBC {
    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = DBConnectionDatasourceJDBC.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("File db.properties non trovato");
            }
            props.load(input);
        }
        return props;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String driverClassName = props.getProperty("db.driverClassName");

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception exc) {
            exc.printStackTrace();
            connection = null;  //setta connection a null
        }
        return connection;
    }
}

