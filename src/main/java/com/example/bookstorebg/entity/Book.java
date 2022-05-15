package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Book {
    @JSONField(name = "id")
    private Long id;

    @JSONField(name = "name")
    private String name;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "author")
    private String author;
    @JSONField(name = "price")
    private Double price;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "inventory")
    private Long inventory;
    @JSONField(name = "image")
    private String image;


    public Book(Long id, String name, String type, String author, Double price,
                String description, Long inventory, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.image = image;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Long getInventory() {
        return inventory;
    }
    public void setInventory(Long inventory) {
        this.inventory = inventory;
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
                "Book[id=%d, name='%s', author='%s', type='%s', price=%.2f, description='%s', inventory=%d]",
                id, name, author, type, price, description, inventory);
    }
}
