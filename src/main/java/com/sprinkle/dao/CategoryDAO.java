package com.sprinkle.dao;

import com.sprinkle.models.CategoryModel;
import com.sprinkle.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection conn;

    public CategoryDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // Mendapatkan semua kategori
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        String query = "SELECT * FROM categories ORDER BY category_id ASC";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CategoryModel category = new CategoryModel(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // Tambah kategori baru
    public boolean insertCategory(CategoryModel category) {
        String query = "INSERT INTO categories (category_name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getCategoryName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hapus kategori
    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update nama kategori
    public boolean updateCategory(CategoryModel category) {
        String query = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getCategoryId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public List<CategoryModel> searchCategories(String keyword) {
        List<CategoryModel> categories = new ArrayList<>();
        String query = "SELECT * FROM categories WHERE " +
                       "CAST(category_id AS CHAR) LIKE ? OR " +
                       "category_name LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            String searchTerm = "%" + keyword + "%";
            pstmt.setString(1, searchTerm);
            pstmt.setString(2, searchTerm);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CategoryModel category = new CategoryModel(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

}
