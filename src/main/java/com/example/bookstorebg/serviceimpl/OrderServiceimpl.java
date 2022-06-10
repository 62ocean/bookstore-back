package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.dao.OrderDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;

    @Override
    public List<Order> getOrders(Long user_id) {
        return orderDao.getOrders(user_id);
    }

    @Override
    public List<Map<String, Object>> getOrderItems(Long order_id) {
        return orderDao.getOrderItems(order_id);
    }

    @Override
    public void addOrder(Long user_id, Double price, String address, String receiver, String tele) {
        Order order = new Order(user_id, price, address, receiver, tele);
        Order newOrder = orderDao.addOrder(order);

        List<CartItem> list = cartDao.getCartBooksInfo(user_id);
        for (int i = 0; i < list.size(); ++i) {
            System.out.println("***************************");
            System.out.println(list.get(i).toString());
            System.out.println("***************************");
            System.out.println(newOrder.toString());
            OrderItem item = new OrderItem(list.get(i).getBookId(), newOrder.getOrderId(), list.get(i).getNum());

            orderDao.addOrderItem(item);
            cartDao.deleteCartBook(list.get(i).getBookId(), user_id);
        }
    }

}
