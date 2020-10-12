package com.soapsnake.mq.kafka.client;

import com.soapsnake.mq.kafka.pojos.Company;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {

    public static final String consumerGroup = "demo-group";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaProducerDemo.brokerList);
        properties.put("group.id", consumerGroup);
        properties.put("session.timeout.ms", 10000);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("client.id", "hidden-consumer-client-id-zzh-2");
        KafkaConsumer<String, Company> consumer = new KafkaConsumer<String, Company>(properties);
        consumer.subscribe(Arrays.asList(KafkaProducerDemo.topic));
        try {
            while (true) {
                ConsumerRecords<String, Company> records = consumer.poll(100);
                for (ConsumerRecord<String, Company> record : records) {
                    String info = String.format("topic=%s, partition=%s, offset=%d, consumer=%s, country=%s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    System.out.println(info);
                }
                consumer.commitAsync((offsets, exception) -> {
                    if (exception != null) {
                        String error = String.format("Commit failed for offsets {}", offsets, exception);
                        System.out.println(error);
                    }
                });
            }
        } finally {
            consumer.close();
        }
    }

}
