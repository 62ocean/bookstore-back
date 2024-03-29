package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.OrderDao;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDaoimpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void addOrder(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
