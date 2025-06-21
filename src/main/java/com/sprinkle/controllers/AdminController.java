package com.sprinkle.controllers;

import com.sprinkle.dao.AdminDAO;
import com.sprinkle.models.AdminModel;
import java.util.List;

public class AdminController {
    private AdminDAO adminDAO;

    public AdminController() {
        adminDAO = new AdminDAO();
    }

    public List<AdminModel> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }
    
    public List<AdminModel> getAllStaffs() {
        return adminDAO.getAllStaffs();
    }

    public List<AdminModel> searchAdmins(String keyword) {
        return adminDAO.searchAdmins(keyword);
    }
    
    public List<AdminModel> searchStaffs(String keyword) {
        return adminDAO.searchStaffs(keyword);
    }
    
    public boolean add(AdminModel admin) {
        return adminDAO.add(admin);
    }
//    
    public boolean updateAdmin(AdminModel admin) {
        return adminDAO.update(admin);
    }
    
    public boolean delete(int adminId) {
        return adminDAO.delete(adminId);
    }

    public boolean updatePassword(int adminId, String newPassword) {
        return adminDAO.changePassword(adminId, newPassword);
    }
}
