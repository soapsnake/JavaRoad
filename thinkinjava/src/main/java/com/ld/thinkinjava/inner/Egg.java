package com.ld.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class Egg {

    //内部类
    protected class Yolk {
        public Yolk(){
            System.out.println("Egg.Yolk()");
        }
    }

    private Yolk yolk;

    public Egg(){
        System.out.println("new Egg");
        yolk = new Yolk();
    }

}
