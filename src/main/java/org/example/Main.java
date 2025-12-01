package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/exo_database";
        String user = "postgres";
        String password = "password";
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url,user,password);

            ResultSet rs = conn.createStatement().executeQuery("select * from table");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}