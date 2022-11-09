package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.service.BookService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BookServiceimpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    @Cacheable(value = "book", key = "#id")
    public Book findBookById(Long id) {
        System.out.println("从数据库中获取book: " + id);
        return bookDao.findBookById(id);
    }

    @Override
    @Cacheable(value = "books")
    public List<Book> getBooks() {
        System.out.println("从数据库中获取booklist");
        return bookDao.getBooks();
    }

    @Override
    @CachePut(value = "book", key = "#book.getId()")
    @CacheEvict(value = "books", allEntries = true)
    public Book updateBook(Book book) {
        System.out.println("同时更新缓存与数据库中的book: "+book.getId());
        bookDao.updateBook(book);
        return book;
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public Book deleteBook(Long id) {
        System.out.println("同时删除缓存与数据库中的book: "+id);
        Book book = bookDao.findBookById(id);
        if (book.getOrderItems().isEmpty()) bookDao.deleteBook(book);
        else {
            book.setAvailable(0L);
            bookDao.updateBook(book);
        }
        return book;
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

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        /*
         * 设置 bool 查询
         *  ① 设置查询 BoolQueryBuilder
         *  ② 关键词 must(AND), mustNot(NOT), should(OR)
         *  ③ 查询条件 MatchQueryBuilder 分词查询, TermQueryBuilder 不分词查询
         */
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.should(new MatchQueryBuilder("name", keyword));
        boolQueryBuilder.should(new MatchQueryBuilder("type", keyword));
        boolQueryBuilder.should(new MatchQueryBuilder("author", keyword));
        boolQueryBuilder.should(new MatchQueryBuilder("description", keyword));

        /*
         * 设置总查询
         *  ① 设置查询 NativeSearchQueryBuilder
         *  ② 设置查询条件 withQuery(BoolQueryBuilder boolQueryBuilder)
         *  ③ 设置高亮 withHighlightFields(new HighlightBuilder.Field("name").preTags(preTag).postTags(postTag))
         */
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();

        // 查询
        SearchHits<Book> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Book.class);

        // 遍历查询结果
        for (SearchHit<Book> searchHit : searchHits) {
            Book book = searchHit.getContent();
            books.add(book);
        }
        return books;
    }
}


