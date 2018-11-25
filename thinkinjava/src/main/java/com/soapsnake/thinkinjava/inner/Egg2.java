package com.soapsnake.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class Egg2 {

    private Yolk yolk = new Yolk();

    public Egg2() {
        System.out.println("new Egg2()");
    }

    public void insertYolk(Yolk yy) {
        yolk = yy;
    }

    public void g() {
        yolk.f();
    }

    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.yolk()");
        }

        public void f() {
            System.out.println("Egg2.yolk.f()");
        }
    }
}
