package com.soapsnake.thinkinjava.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by soapsnake on 2017/7/16.
 */
public class ChecketList {

    @SuppressWarnings("unchecked")
    private static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {

        //绕过了泛型的编译期检查!!!!!!!!!猫被放进了狗容器当中
        List<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1);

        //对容器添加了警告器,当代码视图绕过编译期检查违法插入元素时就会抛出异常
        List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Pet> pets = Collections.checkedList(new ArrayList<>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());

    }

}
