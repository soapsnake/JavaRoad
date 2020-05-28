package com.soapsnake.lab.concurrence.runnable.thread;

import java.io.IOException;

public class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
            System.out.println(d);
            System.out.println("input: ");
            System.out.println(System.in.read());
        }
    }
}
