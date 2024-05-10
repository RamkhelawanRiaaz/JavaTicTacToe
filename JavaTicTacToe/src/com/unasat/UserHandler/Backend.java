package com.unasat.UserHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Backend {

    public static void DbConnection()
    {
        String dburl = "jdbc:mysql://localhost:3306/dbjavatictactoe";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(dburl, user, password);
            if (connection != null) {
                System.out.println("Successful Connection");

            } else {
                System.out.println("Failed Connect");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
        }
    }
}

