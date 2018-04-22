package com.ld.multithread.lock;

public class LockTest {

    public static void main(String[] args) {
        Person a = new Person();

        Person b = new Person();

        Thread t1 = new Thread(() -> {
            try {
                a.doSomeThing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread t1");
        t1.start();


        Thread t2 = new Thread(() -> {
            try {
                b.doSomeThing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread t2");
        t2.start();

        System.out.println("t1, t2 all bean started....");
    }

}
