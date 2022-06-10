package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private CartDao cartDao;

    @Override
    public List<Map<String, Object>> getCartBooks(Long user_id) {
        return cartDao.getCartBooks(user_id);

//        List<CartItem> result = new ArrayList<CartItem>();
//
//        result = jdbcTemplate.query(
//                "SELECT book_id, name, price, num, image " +
//                        "FROM cart_item, book " +
//                        "WHERE user_id = ? AND book.id = cart_item.book_id",
//                (rs, rowNum) -> new CartItem(rs.getLong("book_id"),
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getLong("num"),
//                        rs.getString("image"))
//                ,user_id);
//
//        return result;
    }

    @Override
    public boolean addCartBook(Long book_id, Long user_id) {
        CartItem cart_item = new CartItem(book_id, user_id, new Long(1));


        boolean isExist = cartDao.queryCartBook(book_id, user_id);
        if (!isExist) cartDao.addCartBook(cart_item);

        return isExist;

    }

//    public void addCartBook(Integer book_id, Integer user_id) {
//
//        jdbcTemplate.update("INSERT INTO cart_item VALUES (?,?,?)",
//                user_id, book_id, 1);
//
//    }

    public void deleteCartBook(Long book_id, Long user_id) {
        cartDao.deleteCartBook(book_id, user_id);
    }



}
