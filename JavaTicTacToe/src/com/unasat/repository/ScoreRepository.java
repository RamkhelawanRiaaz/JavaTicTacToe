package com.unasat.repository;

import com.unasat.repository.dbconnection.DBConnection;
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
    public Array get_leaderboard() {
        try {

            String sql = "SELECT * FROM gebruikerscore order by desc ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            Array Leaderboard = connection.createArrayOf("VARCHAR", new Object[]{"1", "2","3"});
            pstmt.setArray(1,Leaderboard);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Leaderboard;
            } else {
                System.out.println("De leaderboard is leeg");
                return null; // Return -1 als er geen gebruiker wordt gevonden
            }
        } catch (SQLException e) {
            System.err.println("Failed to get leaderboard");
            e.printStackTrace();
            return null;
        }
    }
}

