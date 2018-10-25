package com.ld.runnable.threadlocal;

public class Accessor implements Runnable {

    private final int id;

    public Accessor(int idn) {
        id = idn;
        // Auto-generated method stub
    }

    @Override
    public void run() {
        // Auto-generated method stub
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return "#"
                + id
                + ":"
                + ThreadLocalVariableHolder.get();
    }
}
