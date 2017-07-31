package com.ld.thinkinjava.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudun on 2017/7/17.
 */
public class ArrayTest3 {
    public static void main(String[] args) {
        String[] strings = new String[10];

        System.out.println(Arrays.toString(strings));

        strings[3] = "fuck";

        System.out.println(Arrays.toString(strings));

        System.out.println(strings.length);


        int[] ints = new int[10];
        System.out.println(Arrays.toString(ints));
        System.out.println(ints.length);
        ints[4] = 3;
        System.out.println(Arrays.toString(ints));


        int[] ints1;
//        ints1[2] = 3;  //数组没有初始化不能使用

        int[] ints2 = {1,2,3,4,5,6};
        System.out.println(ints2.length);

        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la;
        ls[0] = new ArrayList<String>();
        //泛型检测可以检测出来
//        ls[1] = new ArrayList<Integer>();


    }
}
