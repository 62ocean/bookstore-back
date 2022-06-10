package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;

    private Long num;

    public CartItem(Long bookId, Long userId, Long num) {
        this.userId = userId;
        this.bookId = bookId;
        this.num = num;
    }

}
