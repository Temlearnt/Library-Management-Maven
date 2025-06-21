package com.sprinkle.dao;

import com.sprinkle.utils.DatabaseConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AuthDAO {
    private Connection conn;

    public AuthDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Hash password dengan SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Otentikasi dan ambil role
    public String authenticateAndGetRole(String username, String password) {
        String hashedPassword = hashPassword(password);
        String query = "SELECT role FROM admins WHERE username = ? AND password_hash = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.err.println("Auth error: " + e.getMessage());
        }

        return null; // gagal login
    }
}
