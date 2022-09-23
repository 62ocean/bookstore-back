package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.example.bookstorebg.entity.User;
import com.example.bookstorebg.service.BookService;
import com.example.bookstorebg.service.TimerService;
import com.example.bookstorebg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TimerService timerService;

    @RequestMapping("/login")
    public String findUser(@RequestBody Map<String, Object> o) {
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        User user = userService.findUser(username, password);
        if (user != null) {
            timerService.changeLoginStatus(true);
            timerService.getLoginTime();
        }
//        System.out.println(username);
        System.out.println("login");
        System.out.println(this);
        System.out.println(timerService);
        return JSON.toJSONString(user);
    }
    @RequestMapping("/logout")
    public String logout() {
        System.out.println("logout");
        System.out.println(this);
        System.out.println(timerService);
        timerService.changeLoginStatus(false);
        return JSON.toJSONString(timerService.getLoginTime());
    }

    @RequestMapping("/register")
    public String register(@RequestBody Map<String, Object> o) {
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        String email = (String) o.get("email");
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        boolean ifSuccess = userService.register(username, password, email);
        if (ifSuccess) return JSON.toJSONString(1);
        else return JSON.toJSONString(2);
    }

    @RequestMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/changeUserStatus")
    public String changeUserStatus(@RequestBody Map<String, Object> o) {
        Long userId = Long.valueOf((Integer) o.get("userId"));
        userService.changeUserStatus(userId);
        return JSON.toJSONString(null);
    }

    @RequestMapping("/userStatistics")
    public List<Map<String, Object>> userStatistics(@RequestBody Map<String, Object> o) throws ParseException {
        String datestr1 = (String) o.get("date1");
        String datestr2 = (String) o.get("date2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd HH:mm:ss");
        Timestamp date1 = new Timestamp(sdf.parse(datestr1).getTime());
        Timestamp date2 = new Timestamp(sdf.parse(datestr2).getTime());

        return userService.userStatistics(date1, date2);
    }

    @RequestMapping("/userBookStatistics")
    public List<Map<String, Object>> userBookStatistics(@RequestBody Map<String, Object> o) throws ParseException {
        Long userId = Long.valueOf((Integer) o.get("userId"));
        String datestr1 = (String) o.get("date1");
        String datestr2 = (String) o.get("date2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd HH:mm:ss");
        Timestamp date1 = new Timestamp(sdf.parse(datestr1).getTime());
        Timestamp date2 = new Timestamp(sdf.parse(datestr2).getTime());

        return userService.userBookStatistics(userId, date1, date2);
    }

}
