package com.example.bookstorebg.service;

import com.example.bookstorebg.entity.User;

public interface UserService {

    User findUser(String username, String password);

}
