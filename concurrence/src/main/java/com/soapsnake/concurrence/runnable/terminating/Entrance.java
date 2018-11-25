package com.soapsnake.concurrence.runnable.terminating;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Entrance implements Runnable {

    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private static volatile boolean canceled = false;
    private final int id;
    private int number = 0;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances)
            sum += entrance.getvalue();
        return sum;
    }

    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + "total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (Exception e) {
                //: handle exception
                System.out.println("sleep interrupted : 睡眠中断!!!!!");
            }
        }
        System.out.println("stopping " + this);
    }

    public synchronized int getvalue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance" + id + ": " + getvalue();
    }
}
