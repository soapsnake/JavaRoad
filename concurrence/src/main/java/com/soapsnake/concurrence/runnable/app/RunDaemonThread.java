package com.soapsnake.concurrence.runnable.app;

import com.soapsnake.concurrence.runnable.thread.DaemonThread;

public class RunDaemonThread {

    public static void main(String[] args) {

        DaemonThread dthread = new DaemonThread();
        Thread t = new Thread(dthread);
        //t.setDaemon(true);        //假如该句被注释掉的话,线程的finally块中的代码就会被执行
        t.start();
    }
}
