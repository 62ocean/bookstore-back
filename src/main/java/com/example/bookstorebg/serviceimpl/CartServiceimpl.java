package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.User;
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
    private CartDao cartDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<CartItem> getCartBooks(Long user_id) {
        User user = userDao.findUserById(user_id);
        return user.getCartItems();
    }

    @Override
    public boolean addCartBook(Long book_id, Long user_id) {
        Book book = bookDao.findBookById(book_id);
        User user = userDao.findUserById(user_id);
        CartItem cartItem = cartDao.getCartBook(book, user);
        if (cartItem == null) {
            CartItem cart_item = new CartItem(book, user, 1L);
            cartDao.addCartBook(cart_item);
            return false;
        }
        return true;
    }

    public void deleteCartBook(Long book_id, Long user_id) {
        Book book = bookDao.findBookById(book_id);
        User user = userDao.findUserById(user_id);
        CartItem cartItem = cartDao.getCartBook(book, user);
        if (cartItem != null) cartDao.deleteCartBook(cartItem);
    }

    @Override
    public boolean changeNum(Long book_id, Long user_id, Long num) {
        Book book = bookDao.findBookById(book_id);
        User user = userDao.findUserById(user_id);
        CartItem cartItem = cartDao.getCartBook(book, user);
        cartDao.changeNum(cartItem, num);
        return book.getInventory() >= num;
    }
}
