package com.soapsnake.pattern.structuralPatterns.strategypattern;

public class Yellowpen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用黄色的笔去画: radius:" + radius + " x:" + x + " y:" + y);
    }
}
