/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.sprinkle.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.sprinkle.controllers.LoanController;
import com.sprinkle.dao.AdminDAO;
import com.sprinkle.dao.BookDAO;
import com.sprinkle.dao.MemberDAO;
import com.sprinkle.models.AdminModel;
import com.sprinkle.models.BookModel;
import com.sprinkle.models.LoanModel;
import com.sprinkle.models.MemberModel;
/**
 *
 * @author User
 */
public class AddLoanForm extends javax.swing.JDialog {
    private LoanController loanController = new LoanController();

    /**
     * Creates new form AddBookForm
     */
    public AddLoanForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
//        initComponents();
        initCustomForm();
    }
    private void  initCustomForm() {

       setTitle("Add New Loan");

        JLabel labelMember = new JLabel("Member:");
        JComboBox<MemberModel> comboMember = new JComboBox<>();

        JLabel labelBook = new JLabel("Book:");
        JComboBox<BookModel> comboBook = new JComboBox<>();

        JLabel labelAdmin = new JLabel("Staff:");
        JComboBox<AdminModel> comboAdmin = new JComboBox<>();

        JLabel labelLoanDate = new JLabel("Loan Date:");
        JTextField textLoanDate = new JTextField(10);

        JLabel labelDueDate = new JLabel("Due Date (auto):");
        JTextField textDueDate = new JTextField(10);

        JLabel labelStatus = new JLabel("Status:");
        JTextField textStatus = new JTextField("Dipinjam");

        // Default Dates
        LocalDate today = LocalDate.now();
        LocalDate due = today.plusWeeks(2);
        textLoanDate.setText(Date.valueOf(today).toString());
        textDueDate.setText(Date.valueOf(due).toString());
        textLoanDate.setEditable(false);
        textDueDate.setEditable(false);
        textStatus.setEditable(false);

        // Load data
        MemberDAO memberDAO = new MemberDAO();
        for (MemberModel m : memberDAO.getAllMembers()) comboMember.addItem(m);

        BookDAO bookDAO = new BookDAO();
        for (BookModel b : bookDAO.getAllBooks()) comboBook.addItem(b);

        AdminDAO adminDAO = new AdminDAO();
        for (AdminModel a : adminDAO.getAllStaffs()) comboAdmin.addItem(a);

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(labelMember, gbc);
        gbc.gridx = 1; panel.add(comboMember, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelBook, gbc);
        gbc.gridx = 1; panel.add(comboBook, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelAdmin, gbc);
        gbc.gridx = 1; panel.add(comboAdmin, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelLoanDate, gbc);
        gbc.gridx = 1; panel.add(textLoanDate, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelDueDate, gbc);
        gbc.gridx = 1; panel.add(textDueDate, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelStatus, gbc);
        gbc.gridx = 1; panel.add(textStatus, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(btnSave, gbc);
        gbc.gridx = 1; panel.add(btnCancel, gbc);

        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);

        btnCancel.addActionListener(e -> dispose());

        btnSave.addActionListener((ActionEvent e) -> {
            try {
                MemberModel member = (MemberModel) comboMember.getSelectedItem();
                BookModel book = (BookModel) comboBook.getSelectedItem();
                AdminModel admin = (AdminModel) comboAdmin.getSelectedItem();

                LoanModel loan = new LoanModel();
                loan.setMemberId(member.getMemberId());
                loan.setBookId(book.getBookId());
                loan.setAdminId(admin.getAdminId());
                loan.setLoanDate(Date.valueOf(today));
                loan.setDueDate(Date.valueOf(due));
                loan.setStatus("Dipinjam");

                boolean success = loanController.addLoan(loan);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Loan saved successfully.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save loan.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
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
            java.util.logging.Logger.getLogger(AddLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddLoanForm dialog = new AddLoanForm(new javax.swing.JFrame(), true);
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
