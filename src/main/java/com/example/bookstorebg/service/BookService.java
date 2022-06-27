package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.Book;
import com.sun.org.glassfish.external.statistics.TimeStatistic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookService {
    Book findBookById(Long id);

    List<Book> getBooks();

    void updateBook(Book book);
    void deleteBook(Long id);

    List<Map<String, Object>> bookStatistics(Timestamp date1, Timestamp date2);
}
