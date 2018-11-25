package com.soapsnake.thinkinjava.inner;

/**
 * Created by soapsnake on 2017/6/18.
 */
public abstract class Glyph {

    Glyph() {
        System.out.println("Glyph() before draw()");

        draw();

        System.out.println("Glyph() after draw()");
    }

    abstract void draw();

}
