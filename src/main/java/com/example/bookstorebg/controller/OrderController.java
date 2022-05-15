package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.example.bookstorebg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrders")
    public String getOrders(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("user_id");

        String orderJson = JSON.toJSONString(orderService.getOrders(user_id));
        return orderJson;
    }

    @RequestMapping("/getOrderItems")
    public String getOrderItems(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("order_id");
        System.out.println(user_id);

        String orderJson = JSON.toJSONString(orderService.getOrderItems(user_id));
        return orderJson;
    }

    @RequestMapping("/receiveOrders")
    public String createOrders(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("user_id");
        Double price = (Double) o.get("price");
        String address = (String) o.get("address");
        String receiver = (String) o.get("receiver");
        String tele = (String) o.get("tele");

        orderService.createOrder(user_id,price,address,receiver,tele);
        return JSON.toJSONString(null);
    }

}
