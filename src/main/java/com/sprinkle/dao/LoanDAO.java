package com.sprinkle.dao;

import com.sprinkle.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.sprinkle.models.LoanModel;

public class LoanDAO {
    private Connection conn;

    public LoanDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mendapatkan 5 transaksi terbaru berdasarkan return_date (hanya buku yang sudah dikembalikan)
    public List<LoanModel> getRecentReturnedBooks() {
        List<LoanModel> loans = new ArrayList<>();
        String query = "SELECT loan_id, member_name, book_title, category_name, admin_name, loan_date, return_date FROM view_all_data WHERE return_date IS NOT NULL ORDER BY return_date DESC LIMIT 5";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoanModel loan = new LoanModel(
                    rs.getInt("loan_id"),
                    0, // Tidak membutuhkan member_id
                    rs.getString("member_name"),
                    0, // Tidak membutuhkan book_id
                    rs.getString("book_title"),
                    rs.getString("category_name"),
                    rs.getString("admin_name"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
    
    // Mendapatkan semua transaksi peminjaman buku, termasuk member_id dan book_id
    public List<LoanModel> getAllLoansBooks() {
        List<LoanModel> loans = new ArrayList<>();
        String query = "SELECT loan_id, member_id, member_name, book_id, book_title, category_name, admin_name, loan_date, return_date FROM view_all_data ORDER BY loan_date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoanModel loan = new LoanModel(
                    rs.getInt("loan_id"),
                    rs.getInt("member_id"), // Tambahkan member_id
                    rs.getString("member_name"),
                    rs.getInt("book_id"), // Tambahkan book_id
                    rs.getString("book_title"),
                    rs.getString("category_name"),
                    rs.getString("admin_name"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
    public List<LoanModel> searchLoans(String category, String keyword) {
    List<LoanModel> loans = new ArrayList<>();
    String query = "SELECT loan_id, member_id, member_name, book_id, book_title, category_name, admin_name, loan_date, return_date FROM view_all_data WHERE ";

    switch (category) {
        case "ID":
            query += "loan_id LIKE ?";
            break;
        case "User":
            query += "member_name LIKE ?";
            break;
        case "Book":
            query += "book_title LIKE ?";
            break;
        case "Status":
            query += "return_date IS " + (keyword.equalsIgnoreCase("Returned") ? "NOT NULL" : "NULL");
            break;
        default:
            return loans;
    }

    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        if (!category.equals("Status")) {
            pstmt.setString(1, "%" + keyword + "%");
        }

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
                rs.getDate("return_date")
            );
            loans.add(loan);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return loans;
}

}
