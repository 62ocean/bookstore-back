package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//    List<Book>的缓存：不能直接当作一个对象缓存，这样会放入一个超长的value，且无法与Book的缓存关联。
//    每次更新Book后，List<Book>都会被当作一个新的K-V放入redis中。
//    两种方式：
//    1.不要利用spring cache的机制，用redisTemplate手写缓存。
//    2.重写getBooks()函数，先调用所有的getBook(id)，再将它们全都放在一个List中返回。

    @RequestMapping("/getbook")
    public Book getBook(@RequestParam(value = "id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping("/updatebook")
    public Book updateBook(@RequestBody Book book) {
//        System.out.println(book.toString());
        bookService.updateBook(book);
        return book;
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

    @RequestMapping("/searchBooks")
    public List<Book> searchBooks(@RequestBody Map<String, Object> o) {
        String keyword = (String) o.get("keyword");
        return bookService.searchBooks(keyword);
    }

}
