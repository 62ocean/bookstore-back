package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.CartItem;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<Map<String, Object>> getCartBooks(Long user_id);
    List<CartItem> getCartBooksInfo(Long user_id);

    void addCartBook(CartItem cart_item);

    void deleteCartBook(Long book_id, Long user_id);

    boolean queryCartBook(Long book_id, Long user_id);
}
