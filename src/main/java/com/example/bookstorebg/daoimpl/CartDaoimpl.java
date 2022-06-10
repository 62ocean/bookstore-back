package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartDaoimpl implements  CartDao{

    @Autowired
    private CartRepository cartRepository;

    public List<Map<String, Object>> getCartBooks(Long user_id) {
        return cartRepository.getCartBooks(user_id);
    }
    public List<CartItem> getCartBooksInfo(Long user_id) {
        return cartRepository.getByUserId(user_id);
    }
    public void addCartBook(CartItem cart_item) {
        cartRepository.save(cart_item);
    }

    public void deleteCartBook(Long book_id, Long user_id) {
//        cartRepository.deleteCartBook(book_id, user_id);
        List<CartItem> list = cartRepository.getByBookIdAndUserId(book_id, user_id);
        if (!list.isEmpty()) {
            cartRepository.delete(list.get(0));
        }
    }


    public boolean queryCartBook(Long book_id, Long user_id) {
        List<CartItem> list = cartRepository.getByBookIdAndUserId(book_id, user_id);
        if (list.isEmpty()) return false;
        else return true;
    }
}
