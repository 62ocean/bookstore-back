package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookDao.findBookById(id);
        if (book.getOrderItems().isEmpty()) bookDao.deleteBook(book);
        else {
            book.setAvailable(0L);
            bookDao.updateBook(book);
        }
//        System.out.println(book.toString());
//        bookDao.deleteBook(book);
    }

    @Override
    public List<Map<String, Object>> bookStatistics(Timestamp date1, Timestamp date2) {
//        System.out.println(date1.toString());
//        System.out.println(date2.toString());
        Map<Book, Long> sales = new HashMap<>();

        for (Book book : bookDao.getBooks()) {
            List<OrderItem> orderItems = book.getOrderItems();
            if (orderItems.isEmpty()) {
                continue;
            }

            Long num = 0L;
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getOrder().getTime().before(date1) || orderItem.getOrder().getTime().after(date2)) {
                    continue;
                }
                num += orderItem.getNum();
            }
            sales.put(book, num);
        }

        List<Map.Entry<Book, Long>> list = new ArrayList<>(sales.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        List<Map<String, Object>> ret = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Map<String, Object> map = new HashMap<>();
            map.put("book", list.get(i).getKey().getName());
            map.put("num", list.get(i).getValue());
            ret.add(map);
        }

        return ret;
    }
}


