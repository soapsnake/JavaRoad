package com.kevin.snake.random;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args)
    {
        Random random = new Random(System.currentTimeMillis());

        //上限
        int lowLimit = 2000;
        //下限
        int upperLimit = 2000;
        int gradient = Math.abs(upperLimit - lowLimit);
        System.out.println( Math.abs(random.nextLong() % gradient) + lowLimit);




    }
}
