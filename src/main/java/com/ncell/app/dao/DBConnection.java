package com.ncell.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection;

    private static String URL = "jdbc:mysql://localhost:3306/ncell";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws Exception{

            if (connection == null) {
                connection = DriverManager.getConnection(URL, username, password);
            }

            return connection;
    }
}
