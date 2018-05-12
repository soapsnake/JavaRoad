package com.ld.leetcode.dynamic;

public class Question198 {
    //动态规划的解题思想,本质上,這是一个 0/1 knapsack 即n/p完全问题:https://www.youtube.com/watch?v=wFP5VHGHFdk
    public int rob(int[] nums) {
        // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
        int ifRobbedPrevious = 0;    // max monney can get if rob current house
        int ifDidntRobPrevious = 0; // max money can get if not rob current house

        for (int i = 0; i < nums.length; i++) {
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int currRobbed = ifDidntRobPrevious + nums[i];
            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious);
            // Update values for the next round
            ifDidntRobPrevious = currNotRobbed;
            ifRobbedPrevious = currRobbed;
        }
        return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
    }

    public static void main(String[] args) {
        Question198 question198 = new Question198();
        int[] robs = new int[]{1, 3, 1};
        System.out.println(question198.rob(robs));
    }
}
