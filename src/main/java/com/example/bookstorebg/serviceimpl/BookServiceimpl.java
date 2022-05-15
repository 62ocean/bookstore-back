package com.example.bookstorebg.serviceimpl;

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
    JdbcTemplate jdbcTemplate;

    public Book findBookById(Long book_id) {
//        System.out.println(book_id);

        List<Book> result = new ArrayList<Book>();

        result = jdbcTemplate.query(
                "SELECT * FROM book WHERE id = ?",
                (rs, rowNum) -> new Book(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getLong("inventory"),
                        rs.getString("image"))
                ,book_id);
        return result.get(0);
    }

    public List<Book> getBooks() {
        List<Book> result = new ArrayList<Book>();

        result = jdbcTemplate.query(
                "SELECT * FROM book",
                (rs, rowNum) -> new Book(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getLong("inventory"),
                        rs.getString("image"))
        );
        return result;
    }

}
