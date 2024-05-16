package com.unasat.TicTacToe;

import java.sql.*;

public class Backend {

    static final String dburl = "jdbc:mysql://localhost:3306/dbjavatictactoe";
    static final String user = "root";
    static final String password = "root";

//    public static boolean sqlresult(String Winner){
//        try{
//            Connection connection = DriverManager.getConnection(dburl, user, password);
//            Statement stmt = connection.createStatement();
//
//            System.out.println("Results opslaan...");
//            String sql = "UPDATE gebruikersresultaat SET ";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//
//            pstmt.setString(1, Voornaam);
//            pstmt.setString(2, Achternaam);
//            pstmt.setString(3, Gebruikersnaam);
//            pstmt.setString(4, Password);
//            pstmt.setString(5, GeboorteDatum);
//        }
//        catch (SQLException e) {
//            System.err.println("Connection failed");
//            e.printStackTrace();
//            return false;
//        }
//    }
}
