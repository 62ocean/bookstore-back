package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface UserService {

    User findUser(String username, String password);
    boolean register(String username, String password, String email);

    List<User> getAllUsers();

    void changeUserStatus(Long userId);

    List<Map<String, Object>> userStatistics(Timestamp date1, Timestamp date2);

    List<Map<String, Object>> userBookStatistics(Long userId, Timestamp date1, Timestamp date2);
}
