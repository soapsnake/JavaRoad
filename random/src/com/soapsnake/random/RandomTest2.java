package com.soapsnake.random;

import java.util.Random;

public class RandomTest2 {

    public static void main(String[] args) {

        Random random = new Random(System.currentTimeMillis());

        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);
        System.out.println(random.nextDouble() * .3);

    }
}
