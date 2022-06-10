package com.example.bookstorebg.daoimpl;

import com.example.bookstorebg.dao.UserDao;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoimpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String username, String password) {
        List<User> list = userRepository.getByUsernameAndPassword(username, password);
        if (list.isEmpty()) return null;
        else return list.get(0);
    }
}
