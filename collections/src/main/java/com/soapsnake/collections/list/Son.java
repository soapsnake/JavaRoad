package com.soapsnake.collections.list;

public class Son extends Person {

    private String name;
    private Integer age;

    Son() {
        this("nihao");
        System.out.println("son 无参构造被调用");
    }
    Son(String name) {
        System.out.println("son 有参构造被调用");
        this.name = name;
    }

    public static void main(String[] args) {
        Son son = new Son();

        System.out.println(son.name);

        int someoen = 1;

        System.out.println(someoen);

        String s = "xbcde";
        System.out.println(s.charAt(4));

        SomeOne someOne = new SomeOne();


        String string = "人ABC们";
        System.out.println("Jieduan = " + string.substring(5));


    }

}
