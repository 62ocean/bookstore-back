package com.example.bookstorebg.serviceimpl;

import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.Book;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;

    public User findUser(String username, String password) {
//        List<User> result = new ArrayList<User>();
//
//        result = jdbcTemplate.query(
//                "SELECT * FROM user WHERE username = ? AND password = ?",
//                (rs, rowNum) -> new User(rs.getLong("user_id"),
//                        rs.getString("username"),
//                        rs.getString("password"))
//                ,username,password);
//
//        if (result.isEmpty()) return null;
//        else return result.get(0);
        return userDao.findUser(username, password);
    }
}
