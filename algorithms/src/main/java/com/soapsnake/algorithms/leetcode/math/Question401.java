package com.soapsnake.algorithms.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-02-11
 */
public class Question401 {

    //leetcode401
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++)
            for (int m = 0; m < 60; m++)
                //这个判断是这个题目的关键
                if (Integer.bitCount(h * 64 + m) == num)
                    //％02d表示一个整数，用零填充，最多2个数字,如果不满10就会在前面添加0
                    times.add(String.format("%d:%02d", h, m));
        return times;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println("i = " + i + " => "  + Integer.bitCount(i));
        }
    }
}
