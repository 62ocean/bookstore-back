package com.example.bookstorebg.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bookstorebg.entity.CartItem;
import com.example.bookstorebg.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/getCartBooks")
    public List<CartItem> getCartBooks(@RequestBody Map<String, Object> o) {
        Long user_id = Long.valueOf((Integer) o.get("user_id"));
        return cartService.getCartBooks(user_id);
    }

    @RequestMapping("/addCartBook")
    public String addCartBook(@RequestBody Map<String, Object> o) {
        Integer user_id = (Integer) o.get("user_id");
        String book_id = (String) o.get("book_id");

        boolean isExist = cartService.addCartBook(Long.valueOf(book_id), Long.valueOf(user_id));

        if (isExist) return JSON.toJSONString(1);
        else return JSON.toJSONString(2);
    }

    @RequestMapping("/deleteCartBook")
    public String deleteCartBook(@RequestBody Map<String, Object> o) {
        Integer user_id = (Integer) o.get("user_id");
        Integer book_id = (Integer) o.get("book_id");

        cartService.deleteCartBook(Long.valueOf(book_id), Long.valueOf(user_id));
        return JSON.toJSONString(null);
    }

    @RequestMapping("/changeCartNum")
    public String changeCartNum(@RequestBody Map<String, Object> o) {
        Integer user_id = (Integer) o.get("user_id");
        Integer book_id = (Integer) o.get("book_id");
        Integer num = (Integer) o.get("num");

        boolean ifLegal = cartService.changeNum(Long.valueOf(book_id), Long.valueOf(user_id), Long.valueOf(num));
        if (ifLegal) return JSON.toJSONString(1);
        else return JSON.toJSONString(2);
    }

}
