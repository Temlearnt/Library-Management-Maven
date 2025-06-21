package com.sprinkle.dao;

import com.sprinkle.models.MemberModel;
import com.sprinkle.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn;

    public MemberDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public boolean add(MemberModel member) {
    String query = "INSERT INTO members (username, name, email, phone,address, registration_date) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, member.getUsername());
        stmt.setString(2, member.getName());
        stmt.setString(3, member.getEmail());
        stmt.setString(4, member.getPhone());
        stmt.setString(5, member.getAddress());
        stmt.setDate(6, member.getRegistrationDate());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean update(MemberModel member) {
        String query = "UPDATE members SET username = ?, name = ?, email = ?, phone = ?, address=? WHERE member_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getUsername());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getPhone());
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, member.getMemberId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int memberId) {
        String query = "DELETE FROM members WHERE member_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<MemberModel> getAllMembers() {
        List<MemberModel> list = new ArrayList<>();
        String query = "SELECT * FROM members";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MemberModel m = new MemberModel(
                    rs.getInt("member_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDate("registration_date")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MemberModel> searchMembers(String keyword) {
        List<MemberModel> list = new ArrayList<>();
        String query = "SELECT * FROM members WHERE username LIKE ? OR name LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MemberModel m = new MemberModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDate("registration_date")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
