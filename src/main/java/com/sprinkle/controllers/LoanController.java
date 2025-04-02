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
        return loanDAO.getAllLoansBooks(); // Perbaikan pemanggilan metode yang benar
    }

    public List<LoanModel> searchLoans(String category, String keyword) {
    return loanDAO.searchLoans(category, keyword);
}

}
