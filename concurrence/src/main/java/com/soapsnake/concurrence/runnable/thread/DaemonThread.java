package com.soapsnake.concurrence.runnable.thread;

public class DaemonThread implements Runnable {

    @Override
    public void run() {

        try {
            System.out.println("Daemon Thread is running");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("should this always run????");
        }
    }
}
