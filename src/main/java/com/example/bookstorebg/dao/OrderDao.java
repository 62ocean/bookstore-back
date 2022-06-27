package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    void addOrder(Order order);

    List<Order> getAllOrders();
}
