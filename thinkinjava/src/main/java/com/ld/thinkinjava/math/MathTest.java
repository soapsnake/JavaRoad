package com.ld.thinkinjava.math;

/**
 * Created by liudun on 2017/6/8.
 */
public class MathTest {

    public static void main(String[] args) {
        //不相等
        Integer i1 = 500;
        Integer i2 = 500;
        System.out.println("i1 == i2: "+(i1 == i2));

        //不相等
        Integer i3 = Integer.valueOf(600);
        Integer i4 = Integer.valueOf(600);
        System.out.println("i3 == i4: "+ (i3 == i4));


        //不相等
        Integer i5 = new Integer(300);
        Integer i6 = new Integer(300);
        System.out.println("i5 == i6: " + (i5 == i6));

        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);

        int i7 = 6553578;
        int i8 = 6553578;

        System.out.println("i7 == i8: "+ (i7 == i8) );

        long i9 = 0;
        Long i10 = new Long(0);
        System.out.println(i10.getClass().getName());


    }

}
