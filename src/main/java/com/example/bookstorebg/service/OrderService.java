package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(Integer user_id);

    List<OrderItem> getOrderItems(Integer order_id);

    void createOrder(Integer user_id, Double price, String address, String receiver, String tele);

}
