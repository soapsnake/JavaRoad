package com.soapsnake.algorithms.leetcode.array;

/**
 *
 * Created on 2020-04-22
 */
public class Question565 {

    //leetcode565
    public int arrayNesting(int[] nums) {
        int max = Integer.MIN_VALUE;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;
            max = Math.max(max, calcLength(nums, i, visited));
        }
        return max;
    }

    private int calcLength(int[] nums, int start, boolean[] visited) {
        int i = start, count = 0;
        while (count == 0 || i != start) {
            visited[i] = true;
            i = nums[i];
            count++;
        }
        return count;
    }

}
