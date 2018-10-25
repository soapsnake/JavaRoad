package com.ld.thinkinjava.inner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by liudun on 2017/6/26.
 */
public class TestCreation {

    public static void main(String[] args) {

        final long REPS = 100000000L;

        long t1 = System.currentTimeMillis();
        System.out.print("HashTable");

        for (long i = 0; i < REPS; i++) {
            new Hashtable<>();
        }

        long t2 = System.currentTimeMillis();
        System.out.println(":" + (t2 - t1));

        t1 = System.currentTimeMillis();
        System.out.print("TreeMap");

        for (long i = 0; i < REPS; i++) {
            new TreeMap<>();
        }

        t2 = System.currentTimeMillis();
        System.out.println(":" + (t2 - t1));

        t1 = System.currentTimeMillis();
        System.out.print("HashMap");

        for (long i = 0; i < REPS; i++) {
            new HashMap<>();
        }

        t2 = System.currentTimeMillis();
        System.out.println(":" + (t2 - t1));

        String[] strings = {"this", "is", "an", "array"};

        List<String> list = Arrays.asList(strings);

        System.out.println(list.getClass().getSimpleName());

        Arrays.sort(strings);

        Arrays.sort(strings, Comparator.comparingInt(String::length));

        System.out.println(Arrays.toString(strings));

        int i = Arrays.binarySearch(strings, "is");

        System.out.println(i);

        Enumeration enumeration = Collections.enumeration(list);
        System.out.println(enumeration.toString());

        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));

        List list1 = Collections.unmodifiableList(list);
        //将会抛出异常,因为list1不能被修改
//        list1.add("woca");

        System.out.println(list1);

        List list2 = Collections.synchronizedList(list);

        list2.add("worcasdsa");

        System.out.println(list2);

        List<Short> list3 = new ArrayList<>();
        list3.add((short) 1);


        f();

    }

    static void f() {
//        throw new Exception("throw from f()!");

        throw new RuntimeException();

    }

    static void a() {
        throw new NullPointerException();
    }

    static void b() {
        throw new Error();
    }
}
