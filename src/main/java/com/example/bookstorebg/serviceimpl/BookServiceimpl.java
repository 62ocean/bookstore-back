package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceimpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Long id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }
}


