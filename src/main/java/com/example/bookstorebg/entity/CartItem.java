package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class CartItem {

    @JSONField(name = "book_id")
    private Long book_id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "price")
    private Double price;
    @JSONField(name = "num")
    private Long num;
    @JSONField(name = "image")
    private String image;

    public CartItem(Long book_id, String name, Double price, Long num, String image) {
        this.book_id = book_id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.image = image;
    }

    //为什么必须要有get和set方法才能正确转化为fastjson对象呢？
    public Long getBookId() {
        return book_id;
    }
    public void setBookId(Long book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }
    public void setNum(Long num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(
                "CartItem[book_id=%d, name='%s', price=%.2f, num=%d]",
                book_id, name, price, num);
    }

}
