package com.example.bookstorebg.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bookstorebg.server.WebSocketServer;
import com.example.bookstorebg.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class OrderListener {
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private WebSocketServer ws;

    @KafkaListener(topics = "orders", groupId = "group_topic_test")
    public void orderListener(ConsumerRecord<String, String> record) {

        //将接收到的消息转化为json object
        JSONObject o = JSONObject.parseObject(record.value());

        //解析json object, 调用orderService下订单
        orderService.addOrder(Long.valueOf((Integer)o.get("user_id")), new BigDecimal((String) o.get("price")),
                (String) o.get("address"), (String) o.get("receiver"), (String) o.get("tele"),
                new Timestamp((long) o.get("time")));

        System.out.println(o);

        //下订单完成后，向topic orderResults发送完成消息
        o.put("status","done");
        kafkaTemplate.send("orderResults", "key", JSON.toJSONString(o));

    }

    @KafkaListener(topics = "orderResults", groupId = "group_topic_test")
    public void topic2Listener(ConsumerRecord<String, String> record) {
        JSONObject o = JSONObject.parseObject(record.value());
        System.out.println(record.value());
//        System.out.println(o.getString("user_id"));
        ws.sendMessageToUser(o.getString("user_id"), o.toJSONString());
    }
}
