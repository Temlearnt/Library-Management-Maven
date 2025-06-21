package com.sprinkle.dao;

import com.sprinkle.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {
    private Connection conn;

    public DashboardDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public int[] getDashboardMetrics() {
        int totalMembers = 0;
        int totalLoans = 0;
        double totalFines = 0.0;
        int totalBooks = 0;


        try {
            String query = """
                SELECT
                    (SELECT COUNT(*) FROM members) AS total_members,
                    (SELECT COUNT(*) FROM loans WHERE status = 'dipinjam') AS total_loans,
                    (SELECT COALESCE(SUM(fine_amount), 0) FROM loans) AS total_fines,
                    (SELECT SUM(total_quantity) FROM books) AS total_books
                """;

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalMembers = rs.getInt("total_members");
                totalLoans = rs.getInt("total_loans");
                totalFines = rs.getDouble("total_fines");
                totalBooks = rs.getInt("total_books");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{ totalMembers, totalLoans, (int) totalFines, totalBooks}; // Total fines dibulatkan
    }
}
