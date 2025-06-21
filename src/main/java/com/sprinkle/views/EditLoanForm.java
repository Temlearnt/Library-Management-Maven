/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.sprinkle.views;

import com.sprinkle.controllers.LoanController;
import com.sprinkle.dao.AdminDAO;
import com.sprinkle.dao.BookDAO;
import com.sprinkle.dao.MemberDAO;
import com.sprinkle.models.AdminModel;
import com.sprinkle.models.BookModel;
import com.sprinkle.models.LoanModel;
import com.sprinkle.models.MemberModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
public class EditLoanForm extends javax.swing.JDialog {
    private LoanController loanController = new LoanController();
    private LoanModel loan;

    /**
     * Creates new form EditLoanForm
     */
    public EditLoanForm(Frame parent, boolean modal, LoanModel loan) {
    super(parent, modal);
    this.loan = loan;
    initCustomForm(); // penting!
    }

 private void initCustomForm() {
        setTitle("Edit Loan");

        JComboBox<MemberModel> comboMember = new JComboBox<>();
        JComboBox<AdminModel> comboAdmin = new JComboBox<>();
        JComboBox<BookModel> comboBook = new JComboBox<>();
        JTextField textLoanDate = new JTextField(loan.getLoanDate().toString(), 10);
        JTextField textDueDate = new JTextField(loan.getDueDate().toString(), 10);
        JTextField textStatus = new JTextField(loan.getStatus(), 20);

        // Isi data ke dropdown
        List<MemberModel> members = new MemberDAO().getAllMembers();
        for (MemberModel m : members) comboMember.addItem(m);
        comboMember.setSelectedItem(
            members.stream().filter(m -> m.getMemberId() == loan.getMemberId()).findFirst().orElse(null)
        );

        List<AdminModel> admins = new AdminDAO().getAllStaffs();
        for (AdminModel a : admins) comboAdmin.addItem(a);
        comboAdmin.setSelectedItem(
            admins.stream().filter(a -> a.getAdminId() == loan.getAdminId()).findFirst().orElse(null)
        );

        List<BookModel> books = new BookDAO().getAllBooks();
        for (BookModel b : books) comboBook.addItem(b);
        comboBook.setSelectedItem(
            books.stream().filter(b -> b.getBookId() == loan.getBookId()).findFirst().orElse(null)
        );

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(new JLabel("Member:"), gbc);
        gbc.gridx = 1; panel.add(comboMember, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(new JLabel("Admin:"), gbc);
        gbc.gridx = 1; panel.add(comboAdmin, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(new JLabel("Book:"), gbc);
        gbc.gridx = 1; panel.add(comboBook, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(new JLabel("Loan Date:"), gbc);
        gbc.gridx = 1; panel.add(textLoanDate, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(new JLabel("Due Date:"), gbc);
        gbc.gridx = 1; panel.add(textDueDate, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(new JLabel("Status:"), gbc);
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

        btnSave.addActionListener(e -> {
            try {
                MemberModel selectedMember = (MemberModel) comboMember.getSelectedItem();
                AdminModel selectedAdmin = (AdminModel) comboAdmin.getSelectedItem();
                BookModel selectedBook = (BookModel) comboBook.getSelectedItem();

                Date loanDate = Date.valueOf(textLoanDate.getText());
                Date dueDate = Date.valueOf(textDueDate.getText());
                String status = textStatus.getText();

                loan.setMemberId(selectedMember.getMemberId());
                loan.setAdminId(selectedAdmin.getAdminId());
                loan.setBookId(selectedBook.getBookId());
                loan.setLoanDate(loanDate);
                loan.setDueDate(dueDate);
                loan.setStatus(status);

                boolean success = loanController.updateLoan(loan);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Loan updated successfully.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update loan.");
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
            java.util.logging.Logger.getLogger(EditLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditLoanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoanModel dummyLoan = new LoanModel(
                    1, // loanId
                    1, // memberId
                    1, // adminId
                    1, // bookId
                    Date.valueOf(LocalDate.now()), // loanDate
                    Date.valueOf(LocalDate.now().plusWeeks(2)), // dueDate
                    "Dipinjam" // status
                );

                EditLoanForm dialog = new EditLoanForm(new javax.swing.JFrame(), true, dummyLoan);
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
