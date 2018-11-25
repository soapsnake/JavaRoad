package com.soapsnake.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class InheritInner extends WithInner.Inner {

    InheritInner(WithInner withInner) {
        withInner.super();
    }

    public static void main(String[] args) {
        WithInner withInner = new WithInner();
        InheritInner inner = new InheritInner(withInner);
    }
}
