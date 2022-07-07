package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> getOrders(Long user_id);

    void addOrder(Long user_id, BigDecimal price, String address, String receiver, String tele, Timestamp time);

    List<Order> getAllOrders();

}
