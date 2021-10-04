package com.deezer.api.helpers;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "123";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "123");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            return null;
        }
    }
}
