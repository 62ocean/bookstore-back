package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.*;
import com.example.bookstorebg.entity.*;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private OrderItemDao orderItemDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> getOrders(Long user_id) {
        User user = userDao.findUserById(user_id);
        return user.getOrders();
    }

    @Override
    @Transactional
    public void addOrder(Long user_id, BigDecimal price, String address, String receiver, String tele, Timestamp time) {
        User user = userDao.findUserById(user_id);
        Order order = new Order(user, price, address, receiver, tele, time);

        //向数据库中添加order
        orderDao.addOrder(order);

        List<CartItem> list = user.getCartItems();
        for (CartItem value : list) {
            OrderItem item = new OrderItem(value.getBook(), value.getNum(), order);

            //捕获addOrderItem的异常，防止其传播到外层导致外层事务回滚
            try {
                //向数据库中添加orderItem
                orderItemDao.addOrderItem(item);
            } catch (Exception ignored) {}

            //减少数据库中的书籍库存
            bookDao.minusInventory(value.getBook(), value.getNum());
        }

        user.getCartItems().removeAll(list); //一对多删除多的一方时，必须先切断实体类中一与多的联系
        //清空该用户的购物车
        cartDao.deleteCartBooksByUser(user);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
