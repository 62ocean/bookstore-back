package com.example.bookstorebg.repository;

import com.example.bookstorebg.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long user_id);

    @Query(value = "select b.name,t.num " +
            "from order_item t, orders o, book b " +
            "where b.id = t.book_id and t.order_id = o.order_id and o.order_id = ?1",nativeQuery = true)
    List<Map<String, Object>> getOrderItems(Long order_id);

}
