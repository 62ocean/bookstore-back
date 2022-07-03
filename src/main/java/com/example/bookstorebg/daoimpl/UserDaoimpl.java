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

    @Override
    public User findUser(String username, String password) {
        List<User> list = userRepository.getByUsernameAndPassword(username, password);
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public User findUserById(Long user_id) {
        return userRepository.getById(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getByType("user");
    }

    @Override
    public void changeUserStatus(User user) {
        Long available = 1 - user.getAvailable();
        user.setAvailable(available);
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
