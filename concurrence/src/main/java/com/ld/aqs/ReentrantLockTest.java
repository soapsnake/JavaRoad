package com.ld.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static class Person{

        String name;
        int age;
        Lock lock;
        Person(){
            lock = new ReentrantLock();
        }

        public void lockMethod(){
            lock.lock();
            Condition condition = lock.newCondition();
            try {
                System.out.println(Thread.currentThread().getName() + " is sleepping!!!");
                Thread.sleep(1000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();

        Thread t1 = new Thread(person::lockMethod, "t1");
        t1.start();

        Thread.sleep(1000L);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                person.lockMethod();
            }
        }, "t2");
        t2.start();

        System.out.println("main");


    }
}
