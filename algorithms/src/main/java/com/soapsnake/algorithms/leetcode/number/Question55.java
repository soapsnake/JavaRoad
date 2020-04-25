package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-22 09:59
 */
public class Question55 {


    public static void main(String[] args) {
        Question55 question55 = new Question55();
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(question55.canJump(nums));
    }

    /**
     * Example 1:
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * <p>
     * Example 2:
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     * jump length is 0, which makes it impossible to reach the last index.
     * <p>
     * 问题的本质是一个索引可以到达，每个先前的索引都必须是可达的。
     */
    public boolean canJump(int[] nums) {
        int reachAble = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachAble) {
                return false;
            }
            reachAble = Math.max(reachAble, i + nums[i]);
        }
        return true;
    }
}
