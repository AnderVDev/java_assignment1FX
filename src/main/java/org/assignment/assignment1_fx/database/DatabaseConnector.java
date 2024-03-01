package org.assignment.assignment1_fx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Utility class to establish a connection to the database.
 * @author  Anderson Victoral
 *  @version 1.0
 *  @since   2024-03-01
 */
public class DatabaseConnector {
    // Database connection details
    private static final java.lang.String URL = "jdbc:mysql://sql3.freesqldatabase.com/sql3687387";
    private static final java.lang.String USER = "sql3687387";
    private static final java.lang.String PASS = "Gw5dvfmuuv";

    /**
     * Establishes a connection to the database.
    */
    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            // Wrap SQLException in a RuntimeException for easier handling
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
