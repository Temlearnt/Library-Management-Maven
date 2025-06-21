package com.sprinkle.dao;

import com.sprinkle.models.BookModel;
import com.sprinkle.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection conn;

    public BookDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mendapatkan semua data buku
    public List<BookModel> getAllBooks() {
        List<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookModel book = new BookModel(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year_published"),
                    rs.getString("isbn"),
                    rs.getInt("category_id"),
                    rs.getInt("total_quantity"),
                    rs.getInt("available_quantity")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Pencarian berdasarkan keyword ke beberapa kolom buku
    public List<BookModel> searchBooks(String keyword) {
        List<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE " +
                "CAST(book_id AS CHAR) LIKE ? OR " +
                "title LIKE ? OR " +
                "author LIKE ? OR " +
                "publisher LIKE ? OR " +
                "isbn LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            String searchTerm = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, searchTerm);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookModel book = new BookModel(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year_published"),
                    rs.getString("isbn"),
                    rs.getInt("category_id"),
                    rs.getInt("total_quantity"),
                    rs.getInt("available_quantity")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public boolean insertBook(BookModel book) {
        String query = "INSERT INTO books (title, author, publisher, year_published, isbn, category_id, total_quantity, available_quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYearPublished());
            stmt.setString(5, book.getIsbn());
            stmt.setInt(6, book.getCategoryId());
            stmt.setInt(7, book.getTotalQuantity());
            stmt.setInt(8, book.getAvailableQuantity());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateBook(BookModel book) {
    String query = "UPDATE books SET title = ?, author = ?, publisher = ?, year_published = ?, isbn = ?, category_id = ?, total_quantity = ?, available_quantity = ? WHERE book_id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getPublisher());
        stmt.setInt(4, book.getYearPublished());
        stmt.setString(5, book.getIsbn());
        stmt.setInt(6, book.getCategoryId());
        stmt.setInt(7, book.getTotalQuantity());
        stmt.setInt(8, book.getAvailableQuantity());
        stmt.setInt(9, book.getBookId()); // penting: identifikasi buku yang mau di-update

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean deleteBook(int bookId) {
    String query = "DELETE FROM books WHERE book_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, bookId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
