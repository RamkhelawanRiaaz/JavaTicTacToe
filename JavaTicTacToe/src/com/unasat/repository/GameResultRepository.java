package com.unasat.repository;

import com.unasat.repository.dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GameResultRepository {

    private final Connection connection;

    public GameResultRepository() {
        this.connection = DBConnection.connectToDB();
    }

    private int getUserId(String username) throws SQLException {
        String sql = "SELECT ID FROM gebruikers WHERE Gebruikersnaam = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID");
        } else {
            throw new SQLException("User not found: " + username);
        }
    }

    public boolean storeGameResult(String winner, String loser) {
        try {
            connection.setAutoCommit(false);

            // Fetch user IDs
            int winnerId = getUserId(winner);
            int loserId = getUserId(loser);

            // Current timestamp
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Insert winner result
            String sqlWinner = "INSERT INTO GebruikersResult (GebruikersID, Score, Datumtijd) VALUES (?, ?, ?)";
            PreparedStatement pstmtWinner = connection.prepareStatement(sqlWinner);
            pstmtWinner.setInt(1, winnerId);
            pstmtWinner.setInt(2, 1); // Score of 1 for winner
            pstmtWinner.setTimestamp(3, currentTimestamp);
            int rowsInsertedWinner = pstmtWinner.executeUpdate();

            // Insert loser result
            String sqlLoser = "INSERT INTO GebruikersResult (GebruikersID, Score, Datumtijd) VALUES (?, ?, ?)";
            PreparedStatement pstmtLoser = connection.prepareStatement(sqlLoser);
            pstmtLoser.setInt(1, loserId);
            pstmtLoser.setInt(2, 0); // Score of 0 for loser
            pstmtLoser.setTimestamp(3, currentTimestamp);
            int rowsInsertedLoser = pstmtLoser.executeUpdate();

            connection.commit();

            return rowsInsertedWinner > 0 && rowsInsertedLoser > 0;
        } catch (SQLException e) {
            System.err.println("Failed to store game result");
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
}

