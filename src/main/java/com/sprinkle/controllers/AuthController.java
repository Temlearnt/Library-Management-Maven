package com.sprinkle.controllers;

import com.sprinkle.dao.AuthDAO;
import com.sprinkle.views.DashboardAdmin;
import com.sprinkle.views.DashboardStaff;
import com.sprinkle.views.LoginForm;

import javax.swing.*;

public class AuthController {
    private AuthDAO authDAO = new AuthDAO();

    public void login(String username, String password, LoginForm loginForm) {
        String role = authDAO.authenticateAndGetRole(username, password);
        if (role != null) {
            if (role.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(null, "Login berhasil sebagai Admin!");
                new DashboardAdmin().setVisible(true);
            } else if (role.equalsIgnoreCase("staff")) {
                JOptionPane.showMessageDialog(null, "Login berhasil sebagai Staff!");
                new DashboardStaff().setVisible(true);
            }
            loginForm.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal! Periksa username dan password.");
        }
    }
}
