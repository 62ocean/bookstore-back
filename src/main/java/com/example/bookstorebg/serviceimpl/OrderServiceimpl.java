package com.example.bookstorebg.serviceimpl;

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

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getOrders(Integer user_id) {

        List<Order> result = new ArrayList<Order>();

        result = jdbcTemplate.query(
                "SELECT * FROM orders WHERE user_id = ?",
                (rs, rowNum) -> new Order(rs.getLong("order_id"),
                        rs.getDouble("price"),
                        rs.getString("address"),
                        rs.getString("receiver"),
                        rs.getString("tele"),
                        rs.getBoolean("invoice"))
                ,user_id);

        return result;
    }

    public List<OrderItem> getOrderItems(Integer order_id) {

        List<OrderItem> result = new ArrayList<OrderItem>();

        result = jdbcTemplate.query(
                "SELECT name, num " +
                        "FROM order_item NATURAL JOIN orders, book " +
                        "WHERE id = book_id AND order_id = ?",
                (rs, rowNum) -> new OrderItem(rs.getString("name"),
                        rs.getLong("num"))
                ,order_id);

        return result;
    }

    public void createOrder(Integer user_id, Double price, String address, String receiver, String tele) {

        String sql = "select count(*) from orders";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);

        jdbcTemplate.update("INSERT INTO orders VALUES (?,?,?,?,?,?,?)",
                count+1, user_id, price, address, receiver, tele, 1);

        List<CartItem> result = new ArrayList<CartItem>();
        System.out.println(2);
        result = jdbcTemplate.query(
                "SELECT book_id, name, price, num, image " +
                        "FROM cart_item, book " +
                        "WHERE user_id = ? AND book.id = cart_item.book_id",
                (rs, rowNum) -> new CartItem(rs.getLong("book_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getLong("num"),
                        rs.getString("image"))
                ,user_id);
        System.out.println(3);
        for (int i = 0; i < result.size(); ++i) {
            String sql0 = "select count(*) from order_item";
            int count0 = jdbcTemplate.queryForObject(sql0, Integer.class);

            jdbcTemplate.update("INSERT INTO order_item VALUES (?,?,?,?)",
                    count0+1, result.get(i).getBookId(), result.get(i).getNum(), count+1);
        }

    }

}
