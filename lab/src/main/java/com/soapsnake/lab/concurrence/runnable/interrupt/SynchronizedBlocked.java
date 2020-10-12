package com.soapsnake.lab.concurrence.runnable.interrupt;

public class SynchronizedBlocked implements Runnable {
    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f();     //用这个线程来锁住请求          //这个线程和下面run方法的线程不是同一个,这个线程执行了同步方法f,导致
                //下面的run方法无法再次进入f,
            }
        }.start();
    }

    public synchronized void f() {
        while (true) {     //永远不释放锁
            Thread.yield();  //让出该线程的执行权
        }
    }

    @Override
    public void run() {
        System.out.println("trying to call f()");
        f();  //这个f方法的锁已经被上面的匿名内部线程拿走,所以到这里这个线程会被挂起来,处于永远等待锁的状态
        System.out.println("执行这句话啊!!!!!!Exiting synchronizedBlock.run()"); //这句话永远不会被执行,
    }
}
