package com.soapsnake.algorithms.leetcode.biner;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-16
 */
public class Question477 {

    //leetcode477
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int num : nums)
                bitCount += (num >> j) & 1;
            total += bitCount * (n - bitCount);
        }
        return total;
    }
}
