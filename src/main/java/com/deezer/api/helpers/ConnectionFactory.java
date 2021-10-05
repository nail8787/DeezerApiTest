package com.deezer.api.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String url = "jdbc:postgresql://localhost:5432/deezer";
    private final String user = "postgres";
    private final String password = "123";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            return null;
        }
    }
}
