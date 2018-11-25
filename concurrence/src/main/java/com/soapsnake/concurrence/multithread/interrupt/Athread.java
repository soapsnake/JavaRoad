package com.soapsnake.concurrence.multithread.interrupt;

public class Athread {

    public static void main(String[] args) {

        Runnable r = () -> {     //runnable是一个接口
            int i = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("state: " + Thread.currentThread().getState());

                System.out.println(i++);
                try {
                    Thread.sleep(1000);
                    System.out.println("state: " + Thread.currentThread().getState());
                } catch (InterruptedException e) {  //或者考虑不用trycatch而是直接throws异常
                    //Auto-generated method stub
                    e.printStackTrace();     //InterruptedException在这里被捕获,因此状态更新为false
                    Thread.currentThread().interrupt(); //手动再interrupt一次,这样就保证不会再进while循环
                    //return;    //或者捕获异常直接retrun方便又省事
                }
            }
            //System.out.println(Thread.currentThread().isInterrupted());  //永远为false
        };

        Thread t = new Thread(r);
        t.start();

        new Thread(() -> System.out.println("hello there!!!!")).start(); //LAMBDA表达式的真正形态

        try {
            System.out.println("main thread is sleep!!!!!!!");
            Thread.sleep(10000);
            System.out.println("main thread is awaking and ready to fuck up t !!!!!!!");
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
