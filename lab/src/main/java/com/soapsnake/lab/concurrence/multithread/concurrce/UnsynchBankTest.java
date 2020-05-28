package com.soapsnake.lab.concurrence.multithread.concurrce;


public class UnsynchBankTest {


    public static final int NACCOUNTS = 5;   //账户100个
    public static final double INITIAL_BALANCE = 1000;  //初始资金
    public static final double MAX_AMOUNT = 1000;  //最大数量
    public static final int DELAY = 1;   //延迟

    public synchronized static void main(String[] args) {
        // Auto-generated method stub
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);  //初始化:账户,初始资金
        for (int i = 0; i < NACCOUNTS; i++) {    //用for循环不停产生新线程
            int fromAccount = i;   //不同的线程,source账户不一样
            Runnable r = () -> {      //一条线程代表一个账户
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());   //转钱,账户随机
                        //double amount = MAX_AMOUNT*Math.random();  //转钱,数额随机
                        double amount = MAX_AMOUNT * Math.random();      //死锁
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (Exception e) {
                    //: handle exception
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }

    }

}
