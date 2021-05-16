package com.soapsnake.algorithms.leetcode.tree.binarytree;


import java.util.PriorityQueue;

/**
 * 
 * Created on 2021-01-31
 */
public class Question1675 {

    //leetcode1675
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = nums.length, mi = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        for (int a : nums) {
            if (a % 2 == 1)
                a *= 2;
            pq.add(-a);
            mi = Math.min(mi, a);
        }
        while (true) {
            int a = -pq.poll();
            res = Math.min(res, a - mi);
            if (a % 2 == 1)
                break;
            mi = Math.min(mi, a / 2);
            pq.add(-a / 2);
        }
        return res;
    }
}
