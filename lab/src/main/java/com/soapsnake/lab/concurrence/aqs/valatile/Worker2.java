package com.soapsnake.lab.concurrence.aqs.valatile;

public class Worker2 implements Runnable {
    Person person;

    Worker2(Person person) {
        this.person = person;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(5000L);
            person.age = 10;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
