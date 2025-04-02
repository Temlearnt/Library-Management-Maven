package com.sprinkle.controllers;

import com.sprinkle.models.UserModel;
import com.sprinkle.views.LoginForm;
import com.sprinkle.views.DashboardAdmin;
import com.sprinkle.views.HomeMember;
import javax.swing.JOptionPane;

public class AuthController {
    private UserModel userModel;

    public AuthController() {
        userModel = new UserModel();
    }

    public void login(String username, String password, LoginForm loginForm) {
        if (userModel.authenticateAdmin(username, password)) {
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai Admin!");
            new DashboardAdmin().setVisible(true);
            loginForm.dispose(); // Tutup form login setelah berhasil
        } else if (userModel.authenticateMember(username, password)) {
            JOptionPane.showMessageDialog(null, "Login berhasil sebagai Member!");
            new HomeMember().setVisible(true);
            loginForm.dispose(); // Tutup form login setelah berhasil
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal! Periksa username dan password.");
        }
    }
}
