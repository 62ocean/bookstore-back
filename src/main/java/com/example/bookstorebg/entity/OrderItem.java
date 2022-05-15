package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderItem {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "num")
    private Long num;

    public OrderItem(String name, Long num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getNum() {
        return num;
    }
    public void setNum(Long num) {
        this.num = num;
    }
}
