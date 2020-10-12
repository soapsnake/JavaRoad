package com.soapsnake.algorithms.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/28 22:41
 */
public class Question217 {

    public static void main(String[] args) {
        Question217 question217 = new Question217();
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(question217.containsDuplicate(nums));
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> collection = new HashSet<>();

        for (int n : nums) {
            if (collection.contains(n)) {
                return true;
            }
            collection.add(n);
        }
        return false;
    }
}
