package com.sprinkle.dao;

import com.sprinkle.models.LoanModel;
import com.sprinkle.utils.DatabaseConnection;
import java.math.BigDecimal;

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
                       "FROM view_all_data ";

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
    
    public boolean addLoan(LoanModel loan) {
        String query = "INSERT INTO loans (member_id, admin_id, book_id, loan_date, due_date, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, loan.getMemberId());
            stmt.setInt(2, loan.getAdminId());
            stmt.setInt(3, loan.getBookId());
            stmt.setDate(4, loan.getLoanDate());
            stmt.setDate(5, loan.getDueDate());
            stmt.setString(6, loan.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting loan: " + e.getMessage());
            return false;
        }
    }

    
    public boolean updateLoan(LoanModel loan) {
    String query = "UPDATE loans SET member_id = ?, admin_id = ?, " +
                   "loan_date = ?, return_date = ?, due_date = ?, " +
                   "fine_amount = ?, status = ? WHERE loan_id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, loan.getMemberId());
        stmt.setInt(2, loan.getAdminId()); // pastikan kamu punya admin_id dalam DAO, meskipun nama admin disimpan juga
        stmt.setDate(3, loan.getLoanDate());
        stmt.setDate(4, loan.getReturnDate());
        stmt.setDate(5, loan.getDueDate());
        stmt.setBigDecimal(6, loan.getFineAmount());
        stmt.setString(7, loan.getStatus());
        stmt.setInt(8, loan.getLoanId());

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Hapus peminjaman berdasarkan loan_id
    public boolean deleteLoan(int loanId) {
        String query = "DELETE FROM loans WHERE loan_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, loanId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting loan: " + e.getMessage());
            return false;
        }
    }
    
}
