package com.sprinkle;

import com.sprinkle.views.LoginForm;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Menjalankan aplikasi di thread UI (EDT - Event Dispatch Thread)
        System.out.println("Hello world");
        
    SwingUtilities.invokeLater(() -> {
    System.out.println("Attempting to show LoginForm...");
    try {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        System.out.println("LoginForm should now be visible.");
    } catch (Exception e) {
        e.printStackTrace(); // Jika terjadi error, ini akan menampilkan pesan error di konsol.
    }
});

    }
}
