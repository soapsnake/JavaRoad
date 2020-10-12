package com.soapsnake.algorithms.orion;

public class Test {

    public static void main(String[] args) {

        Chicken chicken = new Chicken();
        Egg egg = chicken.lay();
        Bird bird = egg.hatch();


        OtherBird otherBird = new OtherBird();
        Egg egg1 = otherBird.lay();
        Bird bird1 = egg1.hatch();

        System.out.println(bird instanceof Chicken);

        System.out.println(bird1 instanceof OtherBird);

        System.out.println(bird instanceof OtherBird);

        egg.hatch();

    }
}
