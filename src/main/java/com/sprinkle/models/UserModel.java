package com.sprinkle.models;

import com.sprinkle.dao.UserDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserModel {
    private UserDAO userDAO;

    public UserModel() {
        userDAO = new UserDAO();
    }

    // Hash password dengan SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Login Admin
    public boolean authenticateAdmin(String username, String password) {
        return userDAO.authenticateUser("admins", username, hashPassword(password));
    }

    // Login Member
    public boolean authenticateMember(String username, String password) {
        return userDAO.authenticateUser("members", username, hashPassword(password));
    }

    // Tambah Admin
    public boolean addAdmin(String username, String password, String fullName, String email, String phone) {
        return userDAO.addUser("admins", username, hashPassword(password), fullName, email, phone);
    }

    // Tambah Member
    public boolean addMember(String username, String password, String fullName, String email, String phone) {
        return userDAO.addUser("members", username, hashPassword(password), fullName, email, phone);
    }
}
