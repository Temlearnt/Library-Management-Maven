package com.sprinkle.controllers;

import com.sprinkle.dao.CategoryDAO;
import com.sprinkle.models.CategoryModel;
import java.util.List;

public class CategoryController {
    private CategoryDAO categoryDAO;

    public CategoryController() {
        categoryDAO = new CategoryDAO();
    }

    public List<CategoryModel> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public List<CategoryModel> searchCategories(String keyword) {
        return categoryDAO.searchCategories(keyword);
    }
    
    public boolean addCategory(CategoryModel category) {
        return categoryDAO.insertCategory(category);
    }
    
    public boolean updateCategory(CategoryModel category) {
        return categoryDAO.updateCategory(category);
    }
    
    public boolean deleteCategory(int categoryId) {
        return categoryDAO.deleteCategory(categoryId);
    }
}
