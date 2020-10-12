package com.soapsnake.algorithms.leetcode.number;

import java.util.HashSet;
import java.util.Set;

public class Question342 {

    //速度非常慢
    public boolean isPowerOfFour(int num) {
        if (num == 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; Math.pow(4, i) <= Integer.MAX_VALUE; i++) {
            set.add((int) Math.pow(4, i));
        }

        if (set.contains(num)) {
            return true;
        }
        return false;
    }
}
