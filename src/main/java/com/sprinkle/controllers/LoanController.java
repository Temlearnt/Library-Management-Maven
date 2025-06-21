package com.sprinkle.controllers;

import com.sprinkle.dao.LoanDAO;
import com.sprinkle.models.LoanModel;
import java.util.List;

public class LoanController {
    private LoanDAO loanDAO;

    public LoanController() {
        loanDAO = new LoanDAO();
    }

    // Ambil data terbaru dari database
    public List<LoanModel> getRecentReturnedBooks() {
        return loanDAO.getRecentReturnedBooks();
    }

    public List<LoanModel> getAllLoansBooks() {
        return loanDAO.getAllLoansBooks();
    }

    public List<LoanModel> searchLoans(String keyword) {
        return loanDAO.searchLoans(keyword);
    }
    
    // ✅ Tambah pinjaman baru
    public boolean addLoan(LoanModel loan) {
        return loanDAO.addLoan(loan);
    }

    
      // ✅ Update pinjaman (harus isi semua data)
    public boolean updateLoan(LoanModel loan) {
        return loanDAO.updateLoan(loan);
    }

    // ✅ Hapus pinjaman
    public boolean deleteLoan(int loanId) {
        return loanDAO.deleteLoan(loanId);
    }
}
