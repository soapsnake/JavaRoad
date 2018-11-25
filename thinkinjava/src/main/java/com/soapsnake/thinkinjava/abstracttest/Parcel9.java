package com.soapsnake.thinkinjava.abstracttest;

/**
 * Created by soapsnake on 2017/6/14.
 */
public class Parcel9 {
    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();
        Destination destination = parcel9.dest("jidjisa", 99.323f);
    }

    public Destination dest(String dest, float price) {
        return new Destination() {
            private int cost;
            private String label = dest;

            //匿名类的构造器
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("over budget!");
                }
            }

            @Override
            public String readLabel() {
                return label;
            }
        };
    }

}

