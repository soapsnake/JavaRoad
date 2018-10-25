package com.ld.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class BigEgg extends Egg {


    public static void main(String[] args) {
        new BigEgg();

    }

    //子类不能覆盖父类型的内部类
    public class Yolk {
        public Yolk() {
            System.out.println("BigEgg.Yolk()");
        }
    }

}
