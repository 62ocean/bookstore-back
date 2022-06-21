package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.User;

import java.util.List;
import java.util.Map;

public interface CartDao {

    CartItem getCartBook(Book book, User user);

    void addCartBook(CartItem cart_item);

    void deleteCartBook(CartItem cart_item);
    void deleteCartBooksByUser(User user);
}
