package com.example.bookstorebg.repository;

import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "select book_id, name, price, num, image " +
        "from cart_item, book " +
        "where user_id = ?1 and book.id = cart_item.book_id", nativeQuery = true)
    List<Map<String, Object>> getCartBooks(Long user_id) ;

    List<CartItem> getByBookIdAndUserId(Long book_id, Long user_id);
    List<CartItem> getByUserId(Long user_id);

}
