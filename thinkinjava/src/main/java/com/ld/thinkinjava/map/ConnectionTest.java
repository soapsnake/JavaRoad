package com.ld.thinkinjava.map;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by liudun on 2017/6/29.
 */
public class ConnectionTest {

    public static void main(String[] args) {
        Random random = new Random(47);

        //使用hashset的话发现结果是排序的,原因不明,treeset的结果也是排了序的
        Set<Integer> integers = new TreeSet<>();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
            integers.add(random.nextInt(47));
        }

        System.out.println(integers);
    }
}
