package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bookstorebg.entity.Order;
import com.example.bookstorebg.entity.OrderItem;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

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
    public String createOrders(@RequestBody JSONObject o) {

        o.put("time", System.currentTimeMillis());
        kafkaTemplate.send("orders","key", JSON.toJSONString(o));

        return JSON.toJSONString(null);
    }

//    @RequestMapping("/send")
//    public void send() {
//        String data = "Tom,Jerry,80";
//        kafkaTemplate.send("orders",  "key", data);
//        System.out.println(data);
//    }


}
