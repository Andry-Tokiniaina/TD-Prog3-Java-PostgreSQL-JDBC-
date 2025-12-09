package org.example;

import java.sql.*;

public class DBConnexion {
    private final String url = "jdbc:postgresql://localhost:5432/product_management_db";
    private final String user = "product_manager_user";
    private final String password = "123456";

    public DBConnexion(){}

    public Connection getDBConnection() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}