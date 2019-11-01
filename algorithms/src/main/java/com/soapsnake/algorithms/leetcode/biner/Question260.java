package com.soapsnake.algorithms.leetcode.biner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Question260 {

    public static void main(String[] args) {
        Question260 question260 = new Question260();

        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(question260.singleNumber(nums)));
    }

    //O(n)版本解法
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums) {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            } else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

    //空间复杂度o(n)版本解法
    public int[] singleNumber2(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        int[] res = new int[set.size()];
        int j = 0;
        for (int i : set) {
            res[j++] = i;
        }
        return res;
    }


}
