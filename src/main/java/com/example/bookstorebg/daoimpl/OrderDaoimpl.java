package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.OrderDao;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.repository.OrderItemRepository;
import com.example.bookstorebg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoimpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> getOrders(Long user_id) {
        return orderRepository.findByUserId(user_id);
    }

    public List<Map<String, Object>> getOrderItems(Long order_id) {
        return orderRepository.getOrderItems(order_id);
    }

    public Order addOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

}
