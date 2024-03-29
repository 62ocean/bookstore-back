package com.example.bookstorebg.repository;

import com.example.bookstorebg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getByUsernameAndPassword(String username, String password);

    List<User> getByType(String type);

    User getByUsername(String username);
}
