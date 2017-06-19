package com.ld.thinkinjava.abstracttest;

/**
 * Created by liudun on 2017/6/14.
 */
public class Parcel9 {
    public Destination dest(String dest,float price){
        return new Destination(){
            private int cost;

            //匿名类的构造器
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("over budget!");
                }
            }
                private String label = dest;

            @Override
                public String readLabel(){
                    return label;
                }
            };
        }

    public static void main(String[] args) {
        Parcel9 parcel9 = new Parcel9();
        Destination destination = parcel9.dest("jidjisa",99.323f);
    }

    }

