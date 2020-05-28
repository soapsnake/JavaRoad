package com.soapsnake.lab.concurrence.multithread.lock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private int age;

    public void doSomeThing() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is in...");
            int i = 0;
            for (; ; ) {
                Thread.sleep(1000);
            }
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
