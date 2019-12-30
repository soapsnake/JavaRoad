package com.soapsnake.algorithms.leetcode.array;

import java.util.TreeSet;

public class Question220 {

    public static void main(String[] args) {
        String string = "ce";
        System.out.println(string.length());

        String string2 = "测";
        System.out.println(string2.length());

    }


    /**
     * Example 1:
     * Input: nums = [1,2,3,1], k = 3, t = 0
     * Output: true
     *
     * Example 2:
     * Input: nums = [1,0,1,1], k = 1, t = 2
     * Output: true
     *
     * Example 3:
     * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
     * Output: false
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //索引的最大差值不能超过k, 值最大差值不能超过t

        return false;
    }
}
