package com.sprinkle.dao;

import com.sprinkle.models.LoanModel;
import com.sprinkle.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    private Connection conn;

    public LoanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mendapatkan 5 transaksi terbaru berdasarkan return_date (hanya buku yang sudah dikembalikan)
    public List<LoanModel> getRecentReturnedBooks() {
        List<LoanModel> loans = new ArrayList<>();
        String query = "SELECT loan_id, member_name, book_title, category_name, admin_name, loan_date, return_date " +
                       "FROM view_all_data " +
                       "WHERE return_date IS NOT NULL " +
                       "ORDER BY return_date DESC LIMIT 5";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoanModel loan = new LoanModel(
                    rs.getInt("loan_id"),
                    0,
                    rs.getString("member_name"),
                    0,
                    rs.getString("book_title"),
                    rs.getString("category_name"),
                    rs.getString("admin_name"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date"),
                    null,
                    null,
                    null
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    // Mendapatkan semua transaksi peminjaman buku, termasuk due_date, fine_amount, status
    public List<LoanModel> getAllLoansBooks() {
        List<LoanModel> loans = new ArrayList<>();
        String query = "SELECT loan_id, member_id, member_name, book_id, book_title, category_name, admin_name, loan_date, return_date, due_date, fine_amount, status " +
                       "FROM view_all_data " +
                       "ORDER BY loan_date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoanModel loan = new LoanModel(
                    rs.getInt("loan_id"),
                    rs.getInt("member_id"),
                    rs.getString("member_name"),
                    rs.getInt("book_id"),
                    rs.getString("book_title"),
                    rs.getString("category_name"),
                    rs.getString("admin_name"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date"),
                    rs.getDate("due_date"),
                    rs.getBigDecimal("fine_amount"),
                    rs.getString("status")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    // Pencarian berdasarkan keyword ke beberapa kolom
    public List<LoanModel> searchLoans(String keyword) {
        List<LoanModel> loans = new ArrayList<>();
        String query = "SELECT loan_id, member_id, member_name, book_id, book_title, category_name, admin_name, loan_date, return_date, due_date, fine_amount, status " +
                       "FROM view_all_data " +
                       "WHERE CAST(loan_id AS CHAR) LIKE ? " +
                       "OR member_name LIKE ? " +
                       "OR book_title LIKE ? " +
                       "OR status LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);
            pstmt.setString(3, searchTerm);
            pstmt.setString(4, searchTerm);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LoanModel loan = new LoanModel(
                    rs.getInt("loan_id"),
                    rs.getInt("member_id"),
                    rs.getString("member_name"),
                    rs.getInt("book_id"),
                    rs.getString("book_title"),
                    rs.getString("category_name"),
                    rs.getString("admin_name"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date"),
                    rs.getDate("due_date"),
                    rs.getBigDecimal("fine_amount"),
                    rs.getString("status")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }
    
    public boolean updateStatus(int loanId, String newStatus) {
    String query = "UPDATE loans SET status = ? WHERE loan_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, newStatus);
        stmt.setInt(2, loanId);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
