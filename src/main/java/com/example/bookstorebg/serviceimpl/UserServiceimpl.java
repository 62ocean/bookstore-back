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
    private UserDao userDao;

    public User findUser(String username, String password) {
        return userDao.findUser(username, password);
    }
}
