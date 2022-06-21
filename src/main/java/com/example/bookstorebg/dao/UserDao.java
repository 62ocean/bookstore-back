package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.User;

public interface UserDao {
    User findUser(String username, String password);

    User findUserById(Long user_id);
}
