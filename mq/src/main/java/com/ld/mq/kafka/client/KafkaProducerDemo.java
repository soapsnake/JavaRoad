package com.ld.mq.kafka.client;

import com.ld.mq.kafka.pojos.Company;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KafkaProducerDemo {
    public static final String brokerList = "118.184.84.117:9092";
    public static final String topic = "hidden-topic";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.ld.mq.kafka.serializer.DemoSerializer");   //value采用自定义序列化工具
        properties.put("client.id", "hidden-producer-client-id-1");
        properties.put("bootstrap.servers", brokerList);
        properties.put("partitioner.class","com.ld.mq.kafka.partition.DemoPartitioner");

        Producer<String, Company> producer = new KafkaProducer<>(properties);   //这里的value就要改成company类型了

        while (true) {
            Company company = new Company();
            company.setName("hidden.cooperation-" + new Date().getTime());
            company.setAddress("Shanghai, China");
            ProducerRecord<String, Company> producerRecord = new ProducerRecord<>(topic, company);
            try {
                Future<RecordMetadata> future =  producer.send(producerRecord, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        System.out.print(metadata.offset()+"    ");
                        System.out.print(metadata.topic()+"    ");
                        System.out.println(metadata.partition());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
