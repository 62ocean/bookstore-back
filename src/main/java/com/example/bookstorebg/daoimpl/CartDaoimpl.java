package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartDaoimpl implements  CartDao{

    @Autowired
    private CartRepository cartRepository;

    public CartItem getCartBook(Book book, User user) {
        List<CartItem> list = cartRepository.getByBookAndUser(book, user);
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    public void addCartBook(CartItem cart_item) {
        cartRepository.save(cart_item);
    }

    public void deleteCartBook(CartItem cart_item) {
        cartRepository.delete(cart_item);
    }

    public void deleteCartBooksByUser(User user) {
        cartRepository.deleteAllByUser(user);
    }

}
