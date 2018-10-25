package com.ld.runnable.thread;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<String> {
    private int id;

    public CallableThread(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "result of CallableThread" + id;
    }
}
