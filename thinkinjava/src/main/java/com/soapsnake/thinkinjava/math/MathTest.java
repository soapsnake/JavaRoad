package com.soapsnake.thinkinjava.math;

import com.soapsnake.thinkinjava.array.ArrayTest2;

import java.util.Random;

/**
 * Created by soapsnake on 2017/6/8.
 */
public class MathTest {

    public static void main(String[] args) {
        //不相等
        Integer i1 = 500;
        Integer i2 = 500;
        System.out.println("i1 == i2: " + (i1 == i2));

        //不相等
        Integer i3 = Integer.valueOf(600);
        Integer i4 = Integer.valueOf(600);
        System.out.println("i3 == i4: " + (i3 == i4));


        //不相等
        Integer i5 = new Integer(300);
        Integer i6 = new Integer(300);
        System.out.println("i5 == i6: " + (i5 == i6));

        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);

        int i7 = 6553578;
        int i8 = 6553578;

        System.out.println("i7 == i8: " + (i7 == i8));

        long i9 = 0;
        Long i10 = new Long(0);
        System.out.println(i10.getClass().getName());

        ArrayTest2 test2 = new ArrayTest2();
//        test2.testPack();   不允许访问

//        test2.testProtec();   只有通过继承才能访问

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int j = 0;
            j = random.nextInt(40);
            System.out.print(random.nextInt(40) + ",");
            if (j > 40) {
                System.out.println("j > 40: " + j);
            }
        }

        System.out.println("=========");
        int[] a = {1, 3, 23, 43, 545, 234, 32432, 32131};
//        ArrayList<Integer> list = (ArrayList<Integer>) Arrays.asList(a);

//        new String.CASE_INSENSITIVE_ORDER(){};


    }

}
