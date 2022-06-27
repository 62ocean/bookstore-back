package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrders")
    public List<Order> getOrders(@RequestBody Map<String, Object> o) {
        Long user_id = Long.valueOf((Integer)o.get("user_id"));

        return orderService.getOrders(user_id);
    }

    @RequestMapping("/getAllOrders")
    public List<Order> getAllOrders() {
//        System.out.println(orderService.getAllOrders().get(0).toString());
        return orderService.getAllOrders();
    }

    @RequestMapping("/receiveOrders")
    public String createOrders(@RequestBody Map<String, Object> o) {
        Long user_id = Long.valueOf((Integer)o.get("user_id"));
        Double price = (Double) o.get("price");
        String address = (String) o.get("address");
        String receiver = (String) o.get("receiver");
        String tele = (String) o.get("tele");
        Timestamp time = new Timestamp(System.currentTimeMillis());

        orderService.addOrder(user_id, price, address, receiver, tele, time);
        return JSON.toJSONString(null);
    }


}
