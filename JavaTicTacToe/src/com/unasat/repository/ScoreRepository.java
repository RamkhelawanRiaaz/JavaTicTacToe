package com.unasat.repository;

import com.unasat.repository.dbconnection.DBConnection;
import com.unasat.ui.Navigation;

import java.sql.*;


public class ScoreRepository {

    private final Connection connection;

    public ScoreRepository(){
        this.connection = DBConnection.connectToDB();
    }

    public int getWinnerid(String winner) {
        try {
            String sql = "SELECT ID FROM gebruikers WHERE Gebruikersnaam = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, winner);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("Geen gebruiker gevonden met de naam: " + winner);
                return -1; // Return -1 als er geen gebruiker wordt gevonden
            }
        } catch (SQLException e) {
            System.err.println("Failed to get winner ID");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean sqlresult(String winner) {
        int winnerId = getWinnerid(winner);
        if (winnerId == -1) {
            return false; // Winner ID not found
        }

        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE gebruikersresult SET AantalWins = AantalWins + 1 WHERE GebruikersID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, winnerId);
            int rowsUpdated = pstmt.executeUpdate();
            connection.commit();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update game result");
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void get_leaderboard() {
        Navigation navigation = new Navigation();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Leaderboard");
        System.out.println("|-----------------|");
        try {

            String get_scores_sql = "SELECT * FROM gebruikerscore order by Score desc";
            PreparedStatement pstmt = connection.prepareStatement(get_scores_sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String username = "Othniel";

                for (int i = 0; i < 5;)
                {
                    int gebruikersID = rs.getInt("GebruikersID");
                    int score = rs.getInt("Score");


                    String get_gebruikersnaam_sql = "SELECT Gebruikersnaam FROM gebruikers where ID = ?";

                    PreparedStatement pstmt_gebruikersnaam = connection.prepareStatement(get_gebruikersnaam_sql);
                    pstmt_gebruikersnaam.setInt(1,rs.getInt(gebruikersID));

                    ResultSet rs_gebruikersnaam = pstmt_gebruikersnaam.executeQuery();
                    if (rs_gebruikersnaam.next()) {
                        username = rs_gebruikersnaam.getString("Gebruikersnaam");

                    }

                    System.out.println("|" + i+1 +". " + username + " | " + score + "|");
                    System.out.println("|-----------------|");
                    System.out.println(" ");
                    System.out.println(" ");
                    navigation.navigation_handler();


                }

            } else {
                System.out.println("De leaderboard is leeg");
                navigation.navigation_handler();
            }
        } catch (SQLException e) {
            System.err.println("Failed to get leaderboard");
            e.printStackTrace();
            navigation.navigation_handler();
        }
    }
}

