package com.soapsnake.lab.concurrence.runnable.synchronize;

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch dSynch = new DualSynch();
        new Thread() {
            public void run() {
                dSynch.f();
            }
        }.start();
        dSynch.g();
    }
}
