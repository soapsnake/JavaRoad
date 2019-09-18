package com.soapsnake.springboot.service;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaConsumerService {

	@Value("${kafka-topic}")
	private static final  String kafkaTopic = "";

	@KafkaListener(topics = {"binary-in"})
	public void listen(ConsumerRecord<?, ?> record) {
		System.out.println(JSON.toJSONString(record));
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			System.out.println("record =" + record);
			System.out.println("message =" + message);
		}
	}

}
