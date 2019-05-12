package com.soapsnake.algorithms.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

public class Question179 {

    //归根结底其实是dp的思想,局部最优的累积一定会导致结果最优
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = nums[i] + "";
        }

        Comparator<String> comparator = (o1, o2) -> {
            long dest = Long.valueOf(o1 + o2);
            long dest2 = Long.valueOf(o2 + o1);
            return (int) (dest - dest2);
        };
        Arrays.sort(strings, comparator);
        System.out.println(Arrays.toString(strings));
        if (strings[0].equals("0")) {
            return "0";
        }
        StringBuilder stringBuffer = new StringBuilder();
        for (String i : strings) {
            stringBuffer.append(i);
        }
        return stringBuffer.toString();
    }

}
