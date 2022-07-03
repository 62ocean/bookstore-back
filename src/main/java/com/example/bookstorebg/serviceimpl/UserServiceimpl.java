package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }

    @Override
    public boolean register(String username, String password, String email) {
        User existUser = userDao.findUserByUsername(username);
//        System.out.println(existUser);
        if (existUser != null) {
            return false;
        } else {
            User user = new User(username, password, email);
            user.setType("user");
            user.setAvailable(1L);
            userDao.addUser(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void changeUserStatus(Long userId) {
        User user = userDao.findUserById(userId);
        userDao.changeUserStatus(user);
    }

    @Override
    public List<Map<String, Object>> userStatistics(Timestamp date1, Timestamp date2) {
        Map<User, Double> consumptions = new HashMap<>();

        for (User user : userDao.getAllUsers()) {
            Double consumption = 0.0;
            for (Order order : user.getOrders()) {
                if (order.getTime().before(date1) || order.getTime().after(date2)) {
                    continue;
                }
                consumption += order.getPrice();
            }
            consumptions.put(user, consumption);
        }

        List<Map.Entry<User, Double>> list = new ArrayList<>(consumptions.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        List<Map<String, Object>> ret = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Map<String, Object> map = new HashMap<>();
            if (i >= list.size()) {
                map.put("user", "无");
                map.put("consumption", 0.0);
            } else {
                map.put("user", list.get(i).getKey().getUsername());
                map.put("consumption", list.get(i).getValue());
            }
            ret.add(map);
        }
        return ret;
    }

    @Override
    public List<Map<String, Object>> userBookStatistics(Long userId, Timestamp date1, Timestamp date2) {
        User user = userDao.findUserById(userId);
        Map<Book, Long> bookNum = new HashMap<>();

        for (Order order : user.getOrders()) {
            if (order.getTime().before(date1) || order.getTime().after(date2)) {
                continue;
            }
            for (OrderItem orderItem : order.getOrderItems()) {
                if (bookNum.containsKey(orderItem.getBook())) {
                    bookNum.replace(orderItem.getBook(), bookNum.get(orderItem.getBook())+orderItem.getNum());
                } else {
                    bookNum.put(orderItem.getBook(), 1L);
                }
            }
        }
        List<Map.Entry<Book, Long>> list = new ArrayList<>(bookNum.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        List<Map<String, Object>> ret = new ArrayList<>();
        for (Map.Entry<Book, Long> bookLongEntry : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("book", bookLongEntry.getKey().getName());
            map.put("price", bookLongEntry.getKey().getPrice());
            map.put("num", bookLongEntry.getValue());
            ret.add(map);
        }
        return ret;
    }
}
