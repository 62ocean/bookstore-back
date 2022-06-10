package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getbooks")
    public List<Book> getBooks() {

//        String booksJson = JSON.toJSONString(bookService.getBooks());
//
//        return booksJson;
        return bookService.getBooks();
    }

    @RequestMapping("/getbook")
    public Book getBook(@RequestParam(value = "id") Long id) {

//        String bookJson = JSON.toJSONString(bookService.findBookById(id));
//
//        return bookJson;
//        System.out.println(bookService.findBookById(id).toString());
        return bookService.findBookById(id);
    }

}
