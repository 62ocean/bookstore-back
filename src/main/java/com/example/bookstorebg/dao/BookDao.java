package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.Book;

import java.util.List;

public interface BookDao {

    List<Book> getBooks();

    Book findBookById(Long id);

}
