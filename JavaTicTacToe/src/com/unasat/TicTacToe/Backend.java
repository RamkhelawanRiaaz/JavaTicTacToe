package com.unasat.TicTacToe;

import java.sql.*;



public class Backend {

    static final String dburl = "jdbc:mysql://localhost:3306/dbjavatictactoe";
    static final String user = "root";
    static final String password = "root";


//    public static int getWinnerId(String winner){
//        try {
//            Connection connection = DriverManager.getConnection(dburl, user, password);
//            String sql = "SELECT id FROM gebruikers WHERE Gebruikersnaam = ?";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, winner);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                int winnerId = rs.getInt("id");
//                connection.close();
//                return winnerId;
//            } else {
//                System.out.println("Geen gebruiker gevonden met de naam: " + winner);
//                connection.close();
//                return -1; // Return -1 als er geen gebruiker wordt gevonden
//            }
//        } catch (SQLException e) {
//            System.err.println("Failed to get winner ID");
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//    public static boolean sqlresult(String winner) {
//        System.out.println(winner);
//        try {
//            Connection connection = DriverManager.getConnection(dburl, user, password);
//            Statement stmt = connection.createStatement();
//
//
//            String sql = "UPDATE gebruikersresultaat SET ..."; // Voeg hier je UPDATE query toe
//            int rowsAffected = stmt.executeUpdate(sql);
//
//            connection.close();
//
//            System.out.println(winner);
//
//            // Controleer of er rijen zijn beïnvloed
//            if (rowsAffected > 0) {
//                System.out.println("Results succesvol opgeslagen.");
//                return true;
//            } else {
//                System.out.println("Geen results opgeslagen.");
//                return false;
//            }
//        } catch (SQLException e) {
//            System.err.println("Connection failed");
//            e.printStackTrace();
//            return false;
//        }
//    }
}
