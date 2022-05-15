package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Long id);

    List<Book> getBooks();
}
