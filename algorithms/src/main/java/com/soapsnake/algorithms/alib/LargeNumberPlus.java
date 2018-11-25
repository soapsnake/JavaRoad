package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.List;

public class LargeNumberPlus {
    public static String plus(String num1, String num2) {
        //基本思路,用两个字符串来代表这两个数字,对应的位分别执行加法计算,如果满10就进位,最后得到的字符串就代表加法的结果
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int length = chars1.length >= chars2.length ? chars1.length : chars2.length;
        String[] str = new String[length];
        int step = 0;
        for (int i = length - 1; i >= 0; i--) {
            int temp = Integer.parseInt(chars1[i] + "") + Integer.parseInt(chars2[i] + "") + step;
            if (temp - 10 >= 0) {
                str[i] = temp - 10 + step + "";
                step = 1;
            } else {
                str[i] = temp + "";
                step = 0;
            }
        }
        StringBuilder res;
        if (step == 1) {
            res = new StringBuilder("1");
        } else {
            res = new StringBuilder();
        }
        for (String aStr : str) {
            res.append(aStr);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String num1 = "32917321321321";
        String num2 = "83921893281083";
        System.out.println(LargeNumberPlus.plus(num1, num2));
    }

    public List<Integer> getMaxMin() {
        List<Integer> res = new ArrayList<>();

        //基本思路,借助快排的思想,先数一个中位数,然后把比中位数小的数字挪到中位数左边,
        //比中位数大的数字挪到中卫数右边,根据本题目的要求,每次左半边只取比中位数小的,右边只取比中位数大的数子
        //不断进行递归操作,即可的最终结果

        //代码略

        return res;
    }
}
