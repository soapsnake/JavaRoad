package com.soapsnake.lab.thread;

import java.util.Arrays;
import java.util.List;

public class ProduceConsumerModel {

    private final Object lock = new Object();

    private boolean produceReady = false;

    private void produce () {
        List<String> list = Arrays.asList("a", "b" , "c");
        StringBuilder sb = new StringBuilder();

        synchronized (lock) {
            System.out.println("i am the producer, I will create one item for consuming");
            System.out.println("produce completed, consumer please action!!!!");
            this.produceReady = true;
            lock.notify();
        }
    }

    private void consume() throws InterruptedException {
        System.out.println("we are the consumer, but seems there is no data for us to consume!!!!");
        synchronized (lock) {
            while(!produceReady) {
                lock.wait();
            }
        }
        System.out.println("we got signal that the consumer complete it's job, we will start consuming!!!!");
    }

    public static void main(String[] args) {
        ProduceConsumerModel model = new ProduceConsumerModel();
        Thread produceTread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("producer is working....");
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                model.produce();
            }
        });

        Thread consumeTread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    model.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        produceTread.start();
        consumeTread.start();
    }
}
