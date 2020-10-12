package com.soapsnake.algorithms.orion;

public class Egg {
    private Bird bird;
    private boolean isHatched;

    Egg(Bird bird) {
        this.bird = bird;
    }

    public Bird hatch() {
        if (isHatched) {
            throw new IllegalStateException("egg alreay hatched");
        }
        isHatched = true;
        if (bird instanceof Chicken) {
            return new Chicken();
        } else {
            return new OtherBird();
        }
    }
}
