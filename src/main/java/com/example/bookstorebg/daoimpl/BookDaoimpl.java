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

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.getById(id);
    }

}

