package com.unasat.UserHandler;

import java.sql.*;

public class Backend {

    static final String dburl = "jdbc:mysql://localhost:3306/dbjavatictactoe";
    static final String user = "root";
    static final String password = "";


    public static void DbConnection()
    {
        try {
            Connection connection = DriverManager.getConnection(dburl, user, password);
            Statement stmt = connection.createStatement();

            if (connection != null) {
                System.out.println("Successful Connection");
            }

        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
        }
    }

    public static boolean SqlRegister(String Voornaam ,String Achternaam ,String Gebruikersnaam ,String Password ,String GeboorteDatum){
        try {
            Connection connection = DriverManager.getConnection(dburl, user, password);
            Statement stmt = connection.createStatement();

            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO gebruikers (Voornaam, Achternaam, Gebruikersnaam, Password, Geboortedata) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, Voornaam);
            pstmt.setString(2, Achternaam);
            pstmt.setString(3, Gebruikersnaam);
            pstmt.setString(4, Password);
            pstmt.setString(5, GeboorteDatum);

            pstmt.executeUpdate();

            if (connection != null) {

            }
            return true;

        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
            return false;
        }

    }

}

