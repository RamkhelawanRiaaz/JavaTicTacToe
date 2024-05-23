package com.unasat.repository.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost/dbjavatictactoe";


    public static Connection connectToDB() {
        Connection connection =  null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //System.out.println("Connection is Stable and Active: " + connection.getClientInfo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
