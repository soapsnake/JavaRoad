package com.soapsnake.thinkinjava.inner;

/**
 * Created by soapsnake on 2017/6/18.
 */
public class Egg {

    private Yolk yolk;

    public Egg() {
        System.out.println("new Egg");
        yolk = new Yolk();
    }

    //内部类
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg.Yolk()");
        }
    }

}
