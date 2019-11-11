package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.tree.TreeNode;
import org.junit.Test;

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

    //无需数组的最大最小数
    public List<Integer> getMaxMin(int[] arr) {
        List<Integer> res = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return res;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        res.add(min);
        res.add(max);
        return res;
    }

    @Test
    public void testGetMaxMin() {
        int[] arr = {2, 3, 4, 5, 45, 2, 3, 4, 5, 13, 4, 5, 34, 623, 5, 234, 903};
        System.out.println(getMaxMin(arr));

        System.out.println(8921 % 10);
        System.out.println(892 % 10);
        System.out.println(89 % 10);
        System.out.println(8 % 10);
    }

    //非递归斐波拉契数列
    public Long fabici(int n) {
        if (n == 0) {
            return 1L;
        }
        Long first = 1L;
        Long second = 2L;
        Long tar = 0L;
        while (n-- > 2) {
            tar = first + second;
            first = second;
            second = tar;
        }
        return tar;
    }

    @Test
    public void testFacbirci() {
        //1,2,3,5,8,11,19,30,49

        System.out.println(fabici(5));
//        System.out.println(fabici(10));
//        System.out.println(fabici(12));
//        System.out.println(fabici(15));
//        System.out.println(fabici(100));
//        System.out.println(fabici(20));
//        System.out.println(fabici(200));
    }

}
