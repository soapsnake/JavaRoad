package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-05 21:32
 */
public class Question414 {

    //找数组中第三大的数字,如果没有第三大的数字,就返回最大数字
    public int thirdMax(int[] nums) {
        Integer max = null;
        Integer second = null;
        Integer third = null;
        for (Integer i : nums) {
            if (i.equals(max) || i.equals(second) || i.equals(third)) {
                continue;
            }
            if (max == null || i > max) {
                third = second;
                second = max;
                max = i;
            }else if (second == null || i > second) {
                third = second;
                second = i;
            }else if (third == null || i > third) {
                third = i;
            }
        }
        return third == null ? max : third;
    }
}
