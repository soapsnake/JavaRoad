package com.ld.multithread.lock;

public class Person {

    private String name;
    private int age;

    public  void doSomeThing() throws InterruptedException {
        synchronized(this) {
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
