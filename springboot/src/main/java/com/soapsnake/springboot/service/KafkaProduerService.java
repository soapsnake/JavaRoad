package com.soapsnake.springboot.service;


import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.soapsnake.springboot.domain.KafkaMessage;

@Service
public class KafkaProduerService {

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public String send() {
        KafkaMessage message = new KafkaMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send(kafkaTopic, JSON.toJSONString(message));
        return JSON.toJSONString(message);
    }
}
