package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> getBooks();

    Book findBookById(Long id);

    void updateBook(Book book);
    void deleteBook(Book book);

    void minusInventory(Book book, Long num);

}
