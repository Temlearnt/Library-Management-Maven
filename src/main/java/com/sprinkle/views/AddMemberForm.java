/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.sprinkle.views;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.sprinkle.controllers.MemberController;
import com.sprinkle.dao.CategoryDAO;
import com.sprinkle.models.MemberModel;
import com.sprinkle.models.CategoryModel;
import com.toedter.calendar.JDateChooser;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class AddMemberForm extends javax.swing.JDialog {
private MemberController memberController = new MemberController();

    /**
     * Creates new form AddMemberForm
     */
    public AddMemberForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initCustomForm();
    }
    
    private void initCustomForm() {
        setTitle("Add New Member");

        // Komponen input
        JLabel labelName = new JLabel("Name:");
        JTextField textName = new JTextField(30);

        JLabel labelUsername = new JLabel("Username:");
        JTextField textUsername = new JTextField(30);

        JLabel labelPhone = new JLabel("Phone:");
        JTextField textPhone = new JTextField(20);

        JLabel labelEmail = new JLabel("Email:");
        JTextField textEmail = new JTextField(30);

        JLabel labelAddress = new JLabel("Address:");
        JTextField textAddress = new JTextField(40);
        
        JLabel labelRegistrationDate = new JLabel("Registration Date:");
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

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

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelAddress, gbc);
        gbc.gridx = 1; panel.add(textAddress, gbc);
        
        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelRegistrationDate, gbc);
        gbc.gridx = 1; panel.add(dateChooser, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(btnSave, gbc);
        gbc.gridx = 1; panel.add(btnCancel, gbc);

        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);

        MemberController memberController = new MemberController();

        // Tombol aksi
        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
            try {
                // Ambil data dari input form
                String name = textName.getText();
                String username = textUsername.getText();
                String phone = textPhone.getText();
                String email = textEmail.getText();
                String address = textAddress.getText();

                java.util.Date utilDate = dateChooser.getDate();
                if (utilDate == null) {
                    JOptionPane.showMessageDialog(this, "Please select a registration date.");
                    return;
                }

                java.sql.Date registrationDate = new java.sql.Date(utilDate.getTime());

                // Validasi sederhana
                if (name.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name and Username cannot be empty.");
                    return;
                }

                // Buat objek MemberModel
                MemberModel member = new MemberModel(
                    0, // memberId auto-increment
                    name,
                    username,
                    phone,
                    email,
                    address,
                    registrationDate
                );

                // Kirim ke controller
                boolean success = memberController.add(member);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Member saved successfully.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save member.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
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
            java.util.logging.Logger.getLogger(AddMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMemberForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMemberForm dialog = new AddMemberForm(new javax.swing.JFrame(), true);
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
