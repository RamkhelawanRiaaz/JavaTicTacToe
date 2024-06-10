package com.unasat.repository;
import com.unasat.repository.dbconnection.DBConnection;
import java.sql.*;

import com.unasat.ui.Navigation;
import com.unasat.ui.UserHandler;


public class GebruikerRepository {


    private final Connection connection;

    public GebruikerRepository() {
        this.connection = DBConnection.connectToDB();
    }


    public boolean sqlRegister(String Voornaam, String Achternaam, String Gebruikersnaam, String Password, String GeboorteDatum) {
        try {
            connection.setAutoCommit(false); // Start transaction

            String sqlcheck = "SELECT Gebruikersnaam FROM gebruikers WHERE gebruikersnaam = ?";
            PreparedStatement pstmt = connection.prepareStatement(sqlcheck);
            pstmt.setString(1, Gebruikersnaam);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("acc bestaat al");
                UserHandler userHandler = new UserHandler();
                Navigation navigation = new Navigation();

                navigation.navigation_handler();
            } else {
                System.out.println("Inserting records into the table...");
                String sql1 = "INSERT INTO gebruikers (Voornaam, Achternaam, Gebruikersnaam, Password, Geboortedata) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

                pstmt1.setString(1, Voornaam);
                pstmt1.setString(2, Achternaam);
                pstmt1.setString(3, Gebruikersnaam);
                pstmt1.setString(4, Password);
                pstmt1.setString(5, GeboorteDatum);

                // Execute the insert statement
                int affectedRows = pstmt1.executeUpdate();

                // Optionally, handle the generated keys if needed
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = pstmt1.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            long id = generatedKeys.getLong(1);
                            System.out.println("Inserted record's ID: " + id);
                        }
                    }
                }
            }

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("--Connection failed--");
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Reset autocommit to true
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public int GetUserPlayedGames() {
        try {
            // Updated SQL query to sum scores per user
            String userid_sql = "SELECT ID FROM gebruikers WHERE gebruikersnaam = ?;";

            PreparedStatement pstmt = connection.prepareStatement(userid_sql);
            pstmt.setString(1, UserHandler.Player1);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {  // Move cursor to the first row
                int userId = rs.getInt("ID");

                String get_scores_sql = "SELECT COUNT(*) AS score_count FROM gebruikerscore WHERE gebruikersid = ?;";
                PreparedStatement pstmtscores = connection.prepareStatement(get_scores_sql);
                pstmtscores.setInt(1, userId);
                ResultSet rsscores = pstmtscores.executeQuery();

                if (rsscores.next()) {
                    int voltoidegames = rsscores.getInt("score_count");
                    return voltoidegames;
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve played games");
            e.printStackTrace();
        }
        return 0;
    }



    public String SqlLogin(String Gebruikersnaam, String Password) {
        try {

            System.out.println("Inserting records into the table...");
            String sql = "SELECT * FROM gebruikers WHERE Gebruikersnaam = ? AND Password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, Gebruikersnaam);
            pstmt.setString(2, Password);

            ResultSet rs = pstmt.executeQuery();


            if (!rs.next()) {
                System.out.println("Incorrect username of password. Probeer weer");
                return "Failed";
            }
            String username = Gebruikersnaam;

            return username;
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();
            return "Failed";
        }

    }
}
