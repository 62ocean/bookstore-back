package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.Book;
//import com.sun.org.glassfish.external.statistics.TimeStatistic;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface BookService {
    Book findBookById(Long id);

    List<Book> getBooks();

    Book updateBook(Book book);
    Book deleteBook(Long id);

    List<Map<String, Object>> bookStatistics(Timestamp date1, Timestamp date2);

    List<Book> searchBooks(String keyword);
}
