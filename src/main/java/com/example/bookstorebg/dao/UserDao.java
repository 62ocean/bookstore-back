package com.example.bookstorebg.dao;

import com.example.bookstorebg.entity.User;

import java.util.List;

public interface UserDao {
    User findUser(String username, String password);

    User findUserByUsername(String username);

    User findUserById(Long user_id);

    List<User> getAllUsers();

    void changeUserStatus(User user);
    void addUser(User user);
}
