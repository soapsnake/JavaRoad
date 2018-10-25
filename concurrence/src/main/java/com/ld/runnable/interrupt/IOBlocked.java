package com.ld.runnable.interrupt;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {    //IO工作线程

    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }

    @Override
    public void run() {
        try {
            System.out.println("waiting for read(): ");
            in.read();      //读取输入流的数据
        } catch (IOException e) {
            System.out.println("线程抛出了IO异常!!!!");
            if (Thread.currentThread().isInterrupted()) {          //如果这个工作线程被打断
                System.out.println("IO线程收到打断信号interrupted from blocked I/O"); //被I/O流打断
            } else {
                System.out.println("没有收到打断异常,但是收到了运行时异常");
                throw new RuntimeException(e);      //没有打断则抛出运行时异常
            }
        }
        System.out.println("1111111111111111111111111111111111111111EXITING IOblocked.run()");
    }
}
