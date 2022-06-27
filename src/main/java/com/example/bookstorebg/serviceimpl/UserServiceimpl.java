package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.Order;
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
}
