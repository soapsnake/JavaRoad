package com.soapsnake.collections.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liudun on 2017/6/2.
 */
public class SingletonListTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("joe");

        System.out.println(list);

        List list1 = Collections.singletonList("test");

        //singletonList不支持add,remove等操作
//        list1.add("single");
//        list1.remove("test");

        System.out.println(list1);

        System.out.println(list1.size());


    }


}
