package com.soapsnake.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/9.
 */
public class Widget {
    public static class Factory implements FactoryI<Widget> {

        @Override
        public Widget create() {
            return new Widget();
        }
    }
}
