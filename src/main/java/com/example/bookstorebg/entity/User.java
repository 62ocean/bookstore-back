package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(name = "user_id")
    private Long user_id;
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "password")
    private String password;
    
    public User(Long user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[user_id=%d, username='%s', password='%s']",
                user_id, username, password);
    }
}
