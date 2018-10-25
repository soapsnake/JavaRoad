package com.kevin.snake.random;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());

        //上限
        int lowLimit = 2000;
        //下限
        int upperLimit = 2000;
        int gradient = Math.abs(upperLimit - lowLimit);
//        System.out.println( Math.abs(random.nextLong() % gradient) + lowLimit);


//        System.out.println((double)Math.round((random.nextDouble() *.5) *10)/10);

        double traceDiff = (double) Math.round((random.nextDouble() * .3) * 10) / 10;
        double userTrace = 0.8;
        double robotTrace = userTrace + traceDiff;
        while (robotTrace >= 1 || robotTrace < 0.2) {
            //如果机器人的progress超过了0.2 - 1的范围就重新算,这里会不会造成死循环?
            System.out.println("不符合的ruserTrace:" + robotTrace + " traceDiff: " + traceDiff);
            robotTrace = userTrace + (double) Math.round((random.nextDouble() * .3) * 10) / 10;
        }

        System.out.println(traceDiff);


    }
}
