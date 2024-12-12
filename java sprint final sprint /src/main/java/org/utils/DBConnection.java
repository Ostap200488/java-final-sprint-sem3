package org.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage the database connection.
 */
public class DBConnection {
    private static Connection connection = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private DBConnection() {
    }

    /**
     * Establishes and returns a connection to the PostgreSQL database.
     *
     * @return Connection object
     * @throws SQLException           if a database access error occurs
     * @throws ClassNotFoundException if the PostgreSQL JDBC driver is not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            // Hardcoded database configuration
            String url = "jdbc:postgresql://localhost:5432/ecom"; // Replace 'ecom' with your database name
            String username = "postgres"; // Replace with your PostgreSQL username
            String password = "admin"; // Replace with your PostgreSQL password
            String driver = "org.postgresql.Driver";

            // Log the database URL and username (avoid logging passwords)
            System.out.println("Attempting to connect to the database...");
            System.out.println("Database URL: " + url);
            System.out.println("Database Username: " + username);

            // Load PostgreSQL JDBC Driver
            try {
                Class.forName(driver);
                System.out.println("PostgreSQL JDBC Driver Loaded Successfully.");
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver not found.");
                throw e;
            }

            // Establish the connection
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to establish the database connection.");
                throw e;
            }
        }
        return connection;
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }
}
