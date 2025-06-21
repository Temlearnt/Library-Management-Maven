package com.sprinkle.dao;

import com.sprinkle.models.AdminModel;
import com.sprinkle.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private Connection conn;

    public AdminDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public boolean authenticate(String username, String passwordHash) {
        String query = "SELECT * FROM admins WHERE username = ? AND password_hash = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean add(AdminModel admin) {
     String query = "INSERT INTO admins (username, password_hash, name, email, phone, role) " +
                   "VALUES (?, SHA2(?, 256), ?, ?, ?, ?)"; 
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, admin.getUsername());
        stmt.setString(2, admin.getPassword());
        stmt.setString(3, admin.getName());
        stmt.setString(4, admin.getEmail());
        stmt.setString(5, admin.getPhone());
        stmt.setString(6, admin.getRole());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean update(AdminModel admin) {
        String query = "UPDATE admins SET username = ?, name = ?, email = ?, phone = ?, role = ? WHERE admin_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhone());
            stmt.setString(5, admin.getRole());
            stmt.setInt(6, admin.getAdminId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int adminId) {
        String query = "DELETE FROM admins WHERE admin_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, adminId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String authenticateAndGetRole(String username, String passwordHash) {
        String query = "SELECT role FROM admins WHERE username = ? AND password_hash = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AdminModel> getAllAdmins() {
        return getAdminsByRole("admin");
    }

    public List<AdminModel> getAllStaffs() {
        return getAdminsByRole("staff");
    }

    private List<AdminModel> getAdminsByRole(String role) {
        List<AdminModel> list = new ArrayList<>();
        String query = "SELECT * FROM admins WHERE role = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AdminModel a = new AdminModel(
                    rs.getInt("admin_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("role")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AdminModel> searchAdmins(String keyword) {
        return searchAdminsByRole(keyword, "admin");
    }

    public List<AdminModel> searchStaffs(String keyword) {
        return searchAdminsByRole(keyword, "staff");
    }

    private List<AdminModel> searchAdminsByRole(String keyword, String role) {
        List<AdminModel> list = new ArrayList<>();
        String query = "SELECT * FROM admins WHERE role = ? AND (username LIKE ? OR name LIKE ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, role);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AdminModel a = new AdminModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("role")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean changePassword(int adminId, String newPassword) {
        String query = "UPDATE admins SET password_hash = SHA2(?, 256) WHERE admin_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword);  // plaintext password, akan di-hash di SQL
            stmt.setInt(2, adminId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
