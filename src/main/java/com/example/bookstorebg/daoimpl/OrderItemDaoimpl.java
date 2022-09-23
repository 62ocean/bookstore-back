package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.OrderItemDao;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderItemDaoimpl implements OrderItemDao {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void addOrderItem(OrderItem orderItem) {
//        int a = 1 / 0;
        orderItemRepository.saveAndFlush(orderItem);
    }
}
