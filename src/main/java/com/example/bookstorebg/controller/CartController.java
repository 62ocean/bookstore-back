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
    public String getCartBooks(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("user_id");

        String cartJson = JSON.toJSONString(cartService.getCartBooks(user_id));
        return cartJson;
    }

    @RequestMapping("/addCartBook")
    public String addCartBook(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("user_id");
        String book_id = (String) o.get("book_id");

        boolean isExist = cartService.queryCartBook(Integer.valueOf(book_id), user_id);

        if (isExist) return JSON.toJSONString(1);
        else {
            cartService.addCartBook(Integer.valueOf(book_id), user_id);
            return JSON.toJSONString(2);
        }
    }

    @RequestMapping("/deleteCartBook")
    public String deleteCartBook(@RequestBody Map o) {
        Integer user_id = (Integer) o.get("user_id");
        Integer book_id = (Integer) o.get("book_id");

        cartService.deleteCartBook(book_id, user_id);
        return JSON.toJSONString(null);
    }

}
