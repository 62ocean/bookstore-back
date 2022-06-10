package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;
    private Double price;
    private String address;
    private String receiver;
    private String tele;

    public Order(Long user_id, Double price, String address, String receiver, String tele) {
        this.userId = user_id;
        this.price = price;
        this.address = address;
        this.receiver = receiver;
        this.tele = tele;
    }

}
