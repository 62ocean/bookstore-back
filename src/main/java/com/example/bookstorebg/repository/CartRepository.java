package com.example.bookstorebg.repository;

import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> getByBookAndUser(Book book, User user);

    @Transactional
    void deleteAllByUser(User user);
}
