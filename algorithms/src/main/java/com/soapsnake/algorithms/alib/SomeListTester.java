package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Created on 2020-11-18
 */
public class SomeListTester {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();


        Person person = new Person("fds", 1, 2);

        list.add(person);


        List<Person> list2 = new ArrayList<>();

        list.addAll(list2);


        list.parallelStream().forEach(person1 -> {
            System.out.println(person1.name);
        });


        Map<String, Person> map = new HashMap<>();
        map.put("test", null);
    }
}
