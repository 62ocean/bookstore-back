package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getbooks")
    public List<Book> getBooks() {

        return bookService.getBooks();
    }

    @RequestMapping("/getbook")
    public Book getBook(@RequestParam(value = "id") Long id) {

        return bookService.findBookById(id);
    }

    @RequestMapping("/updatebook")
    public String updateBook(@RequestBody Book book) {
//        System.out.println(book.toString());
        bookService.updateBook(book);
        return JSON.toJSONString(null);
    }

    @RequestMapping("/deletebook")
    public String deleteBook(@RequestBody Map<String, Object> o) {
        Integer id = (Integer) o.get("id");
        bookService.deleteBook(Long.valueOf(id));
        return JSON.toJSONString(null);
    }

    @RequestMapping("/bookStatistics")
    public List<Map<String, Object>> bookStatistics(@RequestBody Map<String, Object> o) throws ParseException {
        String datestr1 = (String) o.get("date1");
        String datestr2 = (String) o.get("date2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd HH:mm:ss");
        Timestamp date1 = new Timestamp(sdf.parse(datestr1).getTime());
        Timestamp date2 = new Timestamp(sdf.parse(datestr2).getTime());

        return bookService.bookStatistics(date1, date2);
    }

}
