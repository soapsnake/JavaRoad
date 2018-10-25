package com.ld.lambda.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StreamTest3 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add(i);
        }
        Set<Integer> set = new HashSet<>();
        list.forEach(integer -> {
            set.add(integer);
            if (set.size() >= 10) {
                System.out.println("到10了");
            }
        });

        for (Integer integer : set) {
            System.out.println(integer);
        }


    }
}
