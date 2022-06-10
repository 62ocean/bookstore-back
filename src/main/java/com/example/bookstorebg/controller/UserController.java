package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.service.BookService;
import com.example.bookstorebg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUser")
    public String findUser(@RequestBody Map o) {
        String username = (String) o.get("username");
        String password = (String) o.get("password");
//        System.out.println(username);
        return JSON.toJSONString(userService.findUser(username, password));
    }
}
