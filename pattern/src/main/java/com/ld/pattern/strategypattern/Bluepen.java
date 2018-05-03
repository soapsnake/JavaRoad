package com.ld.pattern.strategypattern;

public class Bluepen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {

        System.out.println("用蓝色的笔去画: radius:"+radius + " x:"+x+" y:"+y);
    }
}
