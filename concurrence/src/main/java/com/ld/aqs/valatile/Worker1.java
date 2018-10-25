package com.ld.aqs.valatile;

public class Worker1 implements Runnable {
    Person person;
    long start;

    Worker1(Person person) {
        this.person = person;
        this.start = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000L);
                System.out.println(person.age);
                if (System.currentTimeMillis() - start > 15 * 1000L) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
