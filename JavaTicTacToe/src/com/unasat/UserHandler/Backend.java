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

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Username already exists");
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
            return false;
        }

    }
    public static String SqlLogin(String Gebruikersnaam ,String Password){
        try {
            Connection connection = DriverManager.getConnection(dburl, user, password);
            Statement stmt = connection.createStatement();

            System.out.println("Inserting records into the table...");
            String sql = "SELECT * FROM gebruikers WHERE Gebruikersnaam = ? AND Password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, Gebruikersnaam);
            pstmt.setString(2, Password);

            ResultSet rs = pstmt.executeQuery();

            String username = rs.getString("Gebruikersnaam");

            if (!rs.next()) {
                System.out.println("Incorrect username or password try again");
                return "Failed";
            }

return username;
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
            return "Failed";
        }

    }
}

