package com.ld.thinkinjava.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liudun on 2017/7/11.
 */
public class App {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();

        Person p1 = new Person();
        p1.setAge(10000);
        p1.setName("dsadsa");
        p1.setStatus(0);
        list.add(p1);

        Person p2 = new Person();
        p2.setAge(20);
        p2.setName("小王");
        p2.setStatus(1);
        list.add(p2);

        Person p3 = new Person();
//        p3.setStatus(0);
        p3.setName("大红");
        p3.setAge(100);
        list.add(p3);

        Person p4 = new Person();
        list.add(p4);

        Person p5 = new Person();
        p5.setStatus(3);
        list.add(p5);

        Person p6 = new Person();
        p6.setStatus(2);
        list.add(p6);

        Person p7 = new Person();
        p7.setStatus(1);
        list.add(p7);

        System.out.println(list);


        System.out.println("======进行排序操作!!!!!!=======");
        Collections.sort(list);
        System.out.println(list);
        for (Person person : list) {
            System.out.println(person.toString());
        }
    }
}
