package com.ncell.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection;

    private static String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static String username = "sa";
    private static String password = "";

    public static Connection getConnection() throws Exception{

            if (connection == null) {
                connection = DriverManager.getConnection(URL, username, password);
            }

            return connection;
    }
}
