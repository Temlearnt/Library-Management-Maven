/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.sprinkle.views;
import com.sprinkle.controllers.AdminController;
import com.sprinkle.models.AdminModel;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.sql.Date;
/**
 *
 * @author User
 */
public class EditAdminForm extends javax.swing.JDialog {
private AdminModel admin;
private AdminController adminController = new AdminController();


    /**
     * Creates new form AddBookForm
     */
    public EditAdminForm(java.awt.Frame parent, boolean modal, AdminModel admin) {
        super(parent, modal);
        this.admin = admin;
        initCustomForm();
    }
    
    private void initCustomForm() {
         setTitle("Update New Admin");

       // Komponen input
        JLabel labelName = new JLabel("Name:");
        JTextField textName = new JTextField(admin.getName(), (30));

        JLabel labelUsername = new JLabel("Username:");
        JTextField textUsername = new JTextField(admin.getUsername(), (30));

        JLabel labelPhone = new JLabel("Phone:");
        JTextField textPhone = new JTextField(admin.getPhone(), (20));

        JLabel labelEmail = new JLabel("Email:");
        JTextField textEmail = new JTextField(admin.getEmail(), (30));

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        JButton btnChangePassword = new JButton("Change Password"); // Tambahan

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(labelName, gbc);
        gbc.gridx = 1; panel.add(textName, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelUsername, gbc);
        gbc.gridx = 1; panel.add(textUsername, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelPhone, gbc);
        gbc.gridx = 1; panel.add(textPhone, gbc);
        
        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelEmail, gbc);
        gbc.gridx = 1; panel.add(textEmail, gbc);

        // Tambahkan tombol-tombol dalam satu panel horizontal
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnChangePassword);

        // Tambahkan ke panel utama
        gbc.gridy = ++row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(buttonPanel, gbc);


        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);

        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            admin.setName(textName.getText());
            admin.setUsername(textUsername.getText());
            admin.setPhone(textPhone.getText());
            admin.setEmail(textEmail.getText());

            boolean success = adminController.updateAdmin(admin);
            if (success) {
                JOptionPane.showMessageDialog(this, "Staff updated successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update admin.");
            }
        });

        btnChangePassword.addActionListener(e -> {
            JPasswordField newPass = new JPasswordField();
            JPasswordField confirmPass = new JPasswordField();
            JPanel passPanel = new JPanel(new GridLayout(2, 2));
            passPanel.add(new JLabel("New Password:"));
            passPanel.add(newPass);
            passPanel.add(new JLabel("Confirm Password:"));
            passPanel.add(confirmPass);

            int option = JOptionPane.showConfirmDialog(this, passPanel, "Change Password", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newPassword = new String(newPass.getPassword());
                String confirmPassword = new String(confirmPass.getPassword());

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords do not match.");
                    return;
                }

                // Gunakan SQL-side hashing (e.g., SHA2)
                boolean success = adminController.updatePassword(admin.getAdminId(), newPassword);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Password updated.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password.");
                }
            }
        });
    
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Buat dummy book atau ambil dari database sesuai kebutuhan
            // Dummy admin
           AdminModel dummyAdmin = new AdminModel(
                1,                  // adminId
                "john_doe",         // username
                "John Doe",         // fullName (Name)
                "john@example.com", // email
                "081234567890",     // phone
                "admin"             // role (sementara bisa admin atau staff)
            );

            EditAdminForm dialog = new EditAdminForm(new javax.swing.JFrame(), true, dummyAdmin);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
