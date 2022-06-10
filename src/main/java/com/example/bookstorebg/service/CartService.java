package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.CartItem;

import java.util.List;
import java.util.Map;

public interface CartService {

    List<Map<String, Object>> getCartBooks(Long user_id);

    boolean addCartBook(Long book_id, Long user_id);

    void deleteCartBook(Long book_id, Long user_id);


}
