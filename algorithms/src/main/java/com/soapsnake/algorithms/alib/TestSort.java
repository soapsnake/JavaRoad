package com.soapsnake.algorithms.alib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2019-12-30
 */
public class TestSort {

    public static void main(String[] args) {
        Person zhansan = new Person("张三", 20, 150);
        Person lisi = new Person("李四", 25, 180);
        Person wangwu = new Person("王五", 38, 120);

        List<Person> lis = new ArrayList<>();
        lis.add(zhansan);
        lis.add(lisi);
        lis.add(wangwu);

        String rankField = "weight";
        List<Person> res = lis.stream().sorted(Comparator.comparingInt(o -> {
                    try {
                        Field field = o.getClass().getDeclaredField(rankField);
                        field.setAccessible(true);
                        return (int) field.get(o);
                    } catch (IllegalAccessException | ClassCastException | NoSuchFieldException e) {
                        e.printStackTrace();
                        return 1;
                    }
                }
        )).collect(Collectors.toList());
        System.out.println(res);

    }
}
