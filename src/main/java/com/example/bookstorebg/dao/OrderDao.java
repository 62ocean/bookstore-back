package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> getOrders(Long user_id);

    List<Map<String, Object>> getOrderItems(Long order_id);

    Order addOrder(Order order);
    void addOrderItem(OrderItem orderItem);
}
