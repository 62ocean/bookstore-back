package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    Long itemId;

    @Column(name = "book_id")
    Long bookId;
    @Column(name = "order_id")
    Long orderId;
    Long num;

    public OrderItem(Long bookId, Long orderId, Long num) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.num = num;
    }
}
