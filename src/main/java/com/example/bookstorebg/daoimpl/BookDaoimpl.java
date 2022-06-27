package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoimpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}

