package com.sprinkle.dao;

import com.sprinkle.utils.DatabaseConnection;
import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Cek login berdasarkan tabel
    public boolean authenticateUser(String table, String username, String passwordHash) {
        String query = "SELECT * FROM " + table + " WHERE username = ? AND password_hash = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Jika ada hasil, berarti login sukses
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tambah Admin atau Member
    public boolean addUser(String table, String username, String passwordHash, String fullName, String email, String phone) {
        String query = "INSERT INTO " + table + " (username, password_hash, full_name, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            stmt.setString(3, fullName);
            stmt.setString(4, email);
            stmt.setString(5, phone);

            return stmt.executeUpdate() > 0; // Berhasil jika > 0
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hapus Admin atau Member
    public boolean deleteUser(String table, int id) {
        String query = "DELETE FROM " + table + " WHERE " + table + "_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0; // Berhasil jika > 0
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
