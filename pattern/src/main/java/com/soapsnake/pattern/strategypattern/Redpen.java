package com.soapsnake.pattern.strategypattern;

public class Redpen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用红色的笔去画: radius:" + radius + " x:" + x + " y:" + y);
    }
}
