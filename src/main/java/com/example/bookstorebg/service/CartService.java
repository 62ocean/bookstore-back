package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.CartItem;

import java.util.List;

public interface CartService {

    List<CartItem> getCartBooks(Integer user_id);

    void addCartBook(Integer book_id, Integer user_id);

    void deleteCartBook(Integer book_id, Integer user_id);

    boolean queryCartBook(Integer book_id, Integer user_id);


}
