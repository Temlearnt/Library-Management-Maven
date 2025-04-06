package com.sprinkle.models;

import java.sql.Date;
import java.math.BigDecimal;

public class LoanModel {
    private int loanId;
    private int memberId;
    private String memberName;
    private int bookId;
    private String bookTitle;
    private String categoryName;
    private String adminName;
    private Date loanDate;
    private Date returnDate;
    private Date dueDate;
    private BigDecimal fineAmount;
    private String status;

    public LoanModel(int loanId, int memberId, String memberName, int bookId, String bookTitle,
                     String categoryName, String adminName, Date loanDate, Date returnDate,
                     Date dueDate, BigDecimal fineAmount, String status) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.categoryName = categoryName;
        this.adminName = adminName;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
        this.fineAmount = fineAmount;
        this.status = status;
    }

    // Getters & Setters
    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public Date getLoanDate() { return loanDate; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public BigDecimal getFineAmount() { return fineAmount; }
    public void setFineAmount(BigDecimal fineAmount) { this.fineAmount = fineAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
