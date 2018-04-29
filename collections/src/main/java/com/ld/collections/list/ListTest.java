package com.ld.collections.list;

import java.util.Arrays;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3);
        System.out.println("ist.get(0) "+list.get(0));

        System.out.println("ist.subList(0,1) " + list.subList(0,1));

        System.out.println("list.subList(0,2) "+list.subList(0,2));

        System.out.println("list.subList(0,3) " +list.subList(0,3));

        List<Integer> subLlist = list.subList(0, 2);
        subLlist.set(1, 10);
        System.out.println("subLlist "+subLlist);
        System.out.println("list" + list);


        list.set(10, 100);
        System.out.println("list.set(10, 100) " + list);


    }


}
