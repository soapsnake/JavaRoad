package com.soapsnake.algorithms.orion;

public class OtherBird implements Bird {

    public OtherBird() {

    }

    @Override
    public Egg lay() {
        Egg egg =  new Egg(this);
        return egg;
    }
}
