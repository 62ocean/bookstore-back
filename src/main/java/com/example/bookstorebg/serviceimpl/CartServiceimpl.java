package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CartItem> getCartBooks(Integer user_id) {
        List<CartItem> result = new ArrayList<CartItem>();

        result = jdbcTemplate.query(
                "SELECT book_id, name, price, num, image " +
                        "FROM cart_item, book " +
                        "WHERE user_id = ? AND book.id = cart_item.book_id",
                (rs, rowNum) -> new CartItem(rs.getLong("book_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getLong("num"),
                        rs.getString("image"))
                ,user_id);

        return result;
    }

    public void addCartBook(Integer book_id, Integer user_id) {

        jdbcTemplate.update("INSERT INTO cart_item VALUES (?,?,?)",
                user_id, book_id, 1);

    }

    public void deleteCartBook(Integer book_id, Integer user_id) {
        jdbcTemplate.update("DELETE FROM cart_item WHERE book_id = ? AND user_id = ?",
                book_id, user_id);
    }

    public boolean queryCartBook(Integer book_id, Integer user_id) {
        List<CartItem> result = new ArrayList<CartItem>();
        result = jdbcTemplate.query(
                "SELECT * " +
                        "FROM cart_item " +
                        "WHERE user_id = ? AND book_id = ?",
                (rs, rowNum) -> new CartItem((Long) (long)1,"1",(Double) (double)1,(Long) (long)1,"1")
                ,user_id, book_id);

        if (result.isEmpty()) return false;
        else return true;
    }

}
