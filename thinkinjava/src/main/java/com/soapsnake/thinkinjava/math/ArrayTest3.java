package com.soapsnake.thinkinjava.math;

import com.soapsnake.thinkinjava.array.ArrayTest2;

/**
 * Created by liudun on 2017/6/10.
 */
public class ArrayTest3 extends ArrayTest2 {

    public static void main(String[] args) {
        ArrayTest2 test2 = new ArrayTest2();
    }

    //protected关键字的唯一作用就是在继承机制里面:允许这个类的子类在任何地方访问这个类
    @Override
    protected void testProtec() {
        super.testProtec();
        System.out.println("从另外一个package中访问某类的protect方法");
    }


}
