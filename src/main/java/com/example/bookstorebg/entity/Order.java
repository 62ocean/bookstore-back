package com.example.bookstorebg.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Order {
    @JSONField(name = "order_id")
    private Long order_id;
    @JSONField(name = "price")
    private Double price;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "receiver")
    private String receiver;
    @JSONField(name = "tele")
    private String tele;
    @JSONField(name = "invoice")
    private Boolean invoice;

    public Order(Long order_id,Double price, String address, String receiver, String tele, Boolean invoice) {
        this.order_id = order_id;
        this.price = price;
        this.address = address;
        this.receiver = receiver;
        this.tele = tele;
        this.invoice = invoice;
    }
    
    public Long getOrderId() {
        return order_id;
    }
    public void setOrderId(Long orderId) {
        this.order_id = order_id;
    }
    
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getInvoice() {
        return invoice;
    }
    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

}
