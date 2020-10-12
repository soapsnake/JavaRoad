package com.soapsnake.algorithms.orion;

public class Chicken implements Bird {

    Chicken() {
    }

    @Override
    public Egg lay() {
        Egg egg = new Egg(this);
        return egg;
    }

}
