package com.soapsnake.pattern.structuralPatterns.genconpattern.checker;


import com.soapsnake.pattern.structuralPatterns.genconpattern.generator.Intgen;

public class Muti10 implements Runnable {

    private final int id;
    private Intgen intgen;

    public Muti10(Intgen intgen, int idenid) {

        this.intgen = intgen;
        this.id = idenid;
    }

    public void run() {
        //  Auto-generated method stub
        while (intgen.isCanceled() == false) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //  Auto-generated catch block
                e.printStackTrace();
            }

            int a = intgen.next();
            System.out.println("this thread is :thread" + id + " number is: " + a);

            if (a % 100 == 0) {
                System.out.println("this word can be 10,20,30......");
                intgen.cancel();
            }
        }
    }
}
