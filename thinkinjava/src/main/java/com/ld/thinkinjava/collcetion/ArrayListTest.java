package com.ld.thinkinjava.collcetion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudun on 2017/7/22.
 */
public class ArrayListTest {

    public static void main(String[] args) {

        String[] strings = {"just", "want", "to", "fuck", "the", "world"};

        System.out.println(strings.length);

        List<String> list = Arrays.asList(strings);

        System.out.println(list.size());

        System.out.println(list);

        //抛异常了,因为这个list是从一个固定尺寸不能更改大小的数组得来的,所以任何会导致容量变化的操作都会抛出异常
//        list.add("can add");

        System.out.println(list);

        List<String> list1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add("dsadsa");
        }

        System.out.println(list1);

        //构造成一个新的ArrayList之后,这个新的list就可以进行容量大小变更的操作了
        List<String> list2 = new ArrayList<>(Arrays.asList(strings));

        list2.add("can add");
        System.out.println(list2);

    }
}
