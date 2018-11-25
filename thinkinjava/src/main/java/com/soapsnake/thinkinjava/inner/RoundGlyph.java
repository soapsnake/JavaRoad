package com.soapsnake.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class RoundGlyph extends Glyph {

    int radius = 1;

    RoundGlyph(int radius) {
        this.radius = radius;
        System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
    }

    /**
     * 运行结果绝对让你打开眼界!!!!!!!!!
     */
    public static void main(String[] args) {

        new RoundGlyph(5);

        Glyph[] glyphs = new Glyph[10];
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph.draw(),radius = " + radius);
    }
}
