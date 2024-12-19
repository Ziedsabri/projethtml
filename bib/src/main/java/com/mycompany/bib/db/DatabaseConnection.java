/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/java"; // Replace with your DB URL
    private static final String USER = "root";  // Database username
    private static final String PASSWORD = "root"; // Database password
    private static Connection connection = null;

    // Method 1 to get a database connection
 /*   public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Database connection failed.", e);
            }
        }
        return connection;
    }*/
    
    // Method 2 to get a database connection
     public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
public static void main(String[] args) {
        try {
            // Attempt to get a database connection
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Database connection successful!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the connection to the database after testing
            DatabaseConnection.closeConnection();
            System.out.println("Connection closed.");
        }
    }
}
 


