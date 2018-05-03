package com.ld.pattern.strategypattern;

public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void excuteDraw(int radius, int x, int y){
        strategy.draw(radius, x, y);
    }
}
