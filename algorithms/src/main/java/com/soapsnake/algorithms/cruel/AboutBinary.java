package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-10
 * JavaRoad
 */
public class AboutBinary {


    /**
     * 二进制常用的运算有:
     * 1. 整数n的二进制表示中第k位(从右往左)是0还是1:  n >> k & 1   (就是把n二进制的k位右移k位然后&1)
     * 2. lowbit(x), 返回x的最后一位1 :  x & -x  //这个可以用来求整数n的二进制表示法中1的个数,类似于Integer.bitCount() ????
     * 3. 原码, 反码, 补码
     */

    public static void main(String[] args) {
        List<String> list = Arrays.asList("beijing", "beijing", "shanghai", "shenzhen");
        Set<String> set = new HashSet<>(list);
        System.out.println(set);

        ArrayList<Integer> list2 = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
    }
}
