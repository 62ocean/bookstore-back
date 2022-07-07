package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.BookDao;
import com.example.bookstorebg.dao.CartDao;
import com.example.bookstorebg.dao.OrderDao;
import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.*;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> getOrders(Long user_id) {
        User user = userDao.findUserById(user_id);
        return user.getOrders();
    }

    @Override
    public void addOrder(Long user_id, Double price, String address, String receiver, String tele, Timestamp time) {
        User user = userDao.findUserById(user_id);
        Order order = new Order(user, price, address, receiver, tele, time);

        List<CartItem> list = user.getCartItems();
        for (CartItem value : list) {
            OrderItem item = new OrderItem(value.getBook(), value.getNum(), order);
            order.addOrderItem(item);
            bookDao.minusInventory(value.getBook(), value.getNum());
        }
        orderDao.addOrder(order);

        //一对多删除多的一方时，必须先切断实体类中一与多的联系
        user.getCartItems().removeAll(list);
        cartDao.deleteCartBooksByUser(user);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
