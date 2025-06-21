package com.sprinkle.controllers;

import com.sprinkle.dao.BookDAO;
import com.sprinkle.models.BookModel;
import java.util.List;

public class BookController {
    private BookDAO bookDAO;

    public BookController() {
        bookDAO = new BookDAO();
    }

    public List<BookModel> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<BookModel> searchBooks(String keyword) {
        return bookDAO.searchBooks(keyword);
    }
    
    public boolean addBook(BookModel book) {
        return bookDAO.insertBook(book);
    }
    
    public boolean updateBook(BookModel book) {
        return bookDAO.updateBook(book);
    }
    
    public boolean deleteBook(int bookId) {
        return bookDAO.deleteBook(bookId);
    }
}
