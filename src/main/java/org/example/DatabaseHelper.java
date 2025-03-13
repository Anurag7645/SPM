package org.example;

import java.sql.*;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database/passwords.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC"); // Load SQLite JDBC Driver
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found: " + e.getMessage());
        }
    }

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void initializeDatabase() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS passwords (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        site TEXT NOT NULL,
                        username TEXT NOT NULL,
                        password TEXT NOT NULL
                    );
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
        }
    }

    public static void savePassword(String site, String username, String password) {
        String encryptedPassword = EncryptionUtil.encrypt(password); // Encrypt before storing

        String insertSQL = "INSERT INTO passwords (site, username, password) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, site);
            pstmt.setString(2, username);
            pstmt.setString(3, encryptedPassword);
            pstmt.executeUpdate();
            System.out.println("Password saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving password: " + e.getMessage());
        }
    }

    public static void getPassword(String site) {
        String querySQL = "SELECT username, password FROM passwords WHERE site = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, site);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String decryptedPassword = EncryptionUtil.decrypt(rs.getString("password"));
                System.out.println("Site: " + site);
                System.out.println("Username: " + username);
                System.out.println("Password: " + decryptedPassword);
            } else {
                System.out.println("No record found for this site.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving password: " + e.getMessage());
        }
    }

}

