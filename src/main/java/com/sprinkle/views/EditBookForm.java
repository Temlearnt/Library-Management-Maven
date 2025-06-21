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
import com.sprinkle.controllers.BookController;
import com.sprinkle.dao.CategoryDAO;
import com.sprinkle.models.BookModel;
import com.sprinkle.models.CategoryModel;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class EditBookForm extends javax.swing.JDialog {
private BookController bookController = new BookController();
private BookModel book;


    /**
     * Creates new form AddBookForm
     */
    public EditBookForm(java.awt.Frame parent, boolean modal, BookModel book) {
        super(parent, modal);
         this.book = book;
//        initComponents();
        initCustomForm();
    }
    private void  initCustomForm() {

        setTitle("Edit New Book");

        // Komponen input
        JLabel labelTitle = new JLabel("Title:");
        JTextField textTitle = new JTextField(book.getTitle(), 30);

        JLabel labelAuthor = new JLabel("Author:");
        JTextField textAuthor = new JTextField(book.getAuthor(), 30);

        JLabel labelPublisher = new JLabel("Publisher:");
        JTextField textPublisher = new JTextField(book.getPublisher(), 30);

        JLabel labelYear = new JLabel("Year:");
        JTextField textYear = new JTextField(String.valueOf(book.getYearPublished()), 10);

        JLabel labelISBN = new JLabel("ISBN:");
        JTextField textISBN = new JTextField(book.getIsbn(), 20);

        JLabel labelCategory = new JLabel("Category ID:");
        JComboBox<CategoryModel> comboCategory = new JComboBox<>();
         CategoryDAO categoryDAO = new CategoryDAO();
         List<CategoryModel> categories = categoryDAO.getAllCategories();

         for (CategoryModel category : categories) {
             comboCategory.addItem(category); // tampil nama, simpan id
         }
         
        for (int i = 0; i < comboCategory.getItemCount(); i++) {
            if (comboCategory.getItemAt(i).getCategoryId() == book.getCategoryId()) {
                comboCategory.setSelectedIndex(i);
                break;
            }
        }

        JLabel labelQty = new JLabel("Qty:");
        JSpinner spinnerQty = new JSpinner(new SpinnerNumberModel(book.getTotalQuantity(), 1, 100, 1));

        JLabel labelAvailableQty = new JLabel("Available Qty:");
        JSpinner spinnerAvailable = new JSpinner(new SpinnerNumberModel(book.getAvailableQuantity(), 0, 100, 1));

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; panel.add(labelTitle, gbc);
        gbc.gridx = 1; panel.add(textTitle, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelAuthor, gbc);
        gbc.gridx = 1; panel.add(textAuthor, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelPublisher, gbc);
        gbc.gridx = 1; panel.add(textPublisher, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelYear, gbc);
        gbc.gridx = 1; panel.add(textYear, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelISBN, gbc);
        gbc.gridx = 1; panel.add(textISBN, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelCategory, gbc);
        gbc.gridx = 1; panel.add(comboCategory, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelQty, gbc);
        gbc.gridx = 1; panel.add(spinnerQty, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(labelAvailableQty, gbc);
        gbc.gridx = 1; panel.add(spinnerAvailable, gbc);

        gbc.gridy = ++row; gbc.gridx = 0; panel.add(btnSave, gbc);
        gbc.gridx = 1; panel.add(btnCancel, gbc);

        getContentPane().removeAll();     // Tambah baris ini
        getContentPane().add(panel);      // Tambah panel kamu
        revalidate();                     // Layout ulang
        repaint();                        // Redraw komponen
        pack();                           // Ukuran otomatis
        setLocationRelativeTo(null);      // Posisikan di tengah

        BookController bookController = new BookController();

        // Tombol aksi
        btnCancel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {
    try {
        // Ambil data dari form input
        String title = textTitle.getText();
        String author = textAuthor.getText();
        String publisher = textPublisher.getText();
        int year = Integer.parseInt(textYear.getText());
        String isbn = textISBN.getText();
        CategoryModel selectedCategory = (CategoryModel) comboCategory.getSelectedItem();
        int categoryId = selectedCategory.getCategoryId();        
        int qty = (Integer) spinnerQty.getValue();
        int availableQty = (Integer) spinnerAvailable.getValue();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Author cannot be empty.");
            return;
        }

        // Update data pada objek book yang sudah ada
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYearPublished(year);
        book.setIsbn(isbn);
        book.setCategoryId(categoryId);
        book.setTotalQuantity(qty);
        book.setAvailableQuantity(availableQty);

        boolean success = bookController.updateBook(book); // method ini perlu kamu buat di controller & DAO

        if (success) {
            JOptionPane.showMessageDialog(this, "Book updated successfully.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update book.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Year must be a number.");
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
            java.util.logging.Logger.getLogger(EditBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditBookForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Buat dummy book atau ambil dari database sesuai kebutuhan
            BookModel dummyBook = new BookModel(0, "Judul Buku", "Penulis", "Penerbit", 2024, "1234567890", 1, 10, 10);

            EditBookForm dialog = new EditBookForm(new javax.swing.JFrame(), true, dummyBook);
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
