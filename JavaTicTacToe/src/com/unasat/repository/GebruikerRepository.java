package com.unasat.repository;

import com.unasat.repository.dbconnection.DBConnection;

import java.sql.*;

public class GebruikerRepository {

    private final Connection connection;

    public GebruikerRepository() {
        this.connection = DBConnection.connectToDB();
    }

    public boolean sqlRegister(String Voornaam, String Achternaam, String Gebruikersnaam, String Password, String GeboorteDatum) {
        try {
            connection.setAutoCommit(false); // Start transaction

            System.out.println("Inserting records into the table...");
            String sql1 = "INSERT INTO gebruikers (Voornaam, Achternaam, Gebruikersnaam, Password, Geboortedata) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt1 = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

            pstmt1.setString(1, Voornaam);
            pstmt1.setString(2, Achternaam);
            pstmt1.setString(3, Gebruikersnaam);
            pstmt1.setString(4, Password);
            pstmt1.setString(5, GeboorteDatum);

            int rowsInserted = pstmt1.executeUpdate();

            if (rowsInserted == 0) {
                System.out.println("Username already exists");
                connection.rollback(); // Rollback transaction
                return false;
            }

            // Get the generated ID for the new record in the 'gebruikers' table
            ResultSet generatedKeys = pstmt1.getGeneratedKeys();
            int gebruikersID = 0;
            if (generatedKeys.next()) {
                gebruikersID = generatedKeys.getInt(1);
            } else {
                System.out.println("Failed to retrieve user ID");
                connection.rollback(); // Rollback transaction
                return false;
            }

            // Insert the user ID into the 'GebruikersResult' table
            String sql2 = "INSERT INTO GebruikersResult (GebruikersID) VALUES (?)";
            PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            pstmt2.setInt(1, gebruikersID);

            int rowsInserted2 = pstmt2.executeUpdate();

            if (rowsInserted2 == 0) {
                System.out.println("--Failed to insert user ID into 'GebruikersResult' table--");
                connection.rollback(); // Rollback transaction
                return false;
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
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
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
