package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
@Document(indexName = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String type;
    @Field(type = FieldType.Keyword, searchAnalyzer = "ik_smart")
    private String author;
    @Field(index = false)
    private BigDecimal price;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String description;
    @Field(index = false)
    private Long inventory;
    @Field(index = false)
    private String image;
    @Field(index = false)
    private Long available;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", inventory=" + inventory +
                ", image='" + image + '\'' +
                ", available=" + available +
                '}';
    }
}
