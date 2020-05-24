package com.soapsnake.lab.collections.map;

import com.soapsnake.lab.collections.list.SomeOne;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {
        //TODO debug
        Map<ClassMates, Integer> map = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            ClassMates mates = ClassMates.builder().id(i).age(i * 10).name(RandomStringUtils.randomAlphabetic(6)).build();
            map.put(mates, i);
        }
        ClassMates mates2 = ClassMates.builder().id(13).age(13 * 10).name(RandomStringUtils.randomAlphabetic(6)).build();
        map.put(mates2, 8);


        Map<ClassMates, Integer> map2 = new ConcurrentHashMap<>();
        map2.put(mates2, 8);

        SomeOne someOne = new SomeOne();


        class A {
            public int anInt = 3;
        }
        Object o = new A();
        A a = (A) o;
        System.out.println("i = " + a.anInt);


    }
}
