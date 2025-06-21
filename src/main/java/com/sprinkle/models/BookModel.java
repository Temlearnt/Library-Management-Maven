package com.sprinkle.models;

public class BookModel {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private String isbn;
    private int categoryId;
    private int totalQuantity;
    private int availableQuantity;

    public BookModel(int bookId, String title, String author, String publisher,
                     int yearPublished, String isbn, int categoryId,
                     int totalQuantity, int availableQuantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
    }

    // Getters & Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getYearPublished() { return yearPublished; }
    public void setYearPublished(int yearPublished) { this.yearPublished = yearPublished; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }

    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    
    @Override
    public String toString() {
        return title;  // atau bisa title + " (" + author + ")"
    }

}
