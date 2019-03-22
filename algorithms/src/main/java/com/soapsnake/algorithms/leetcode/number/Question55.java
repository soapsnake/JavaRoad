package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-22 09:59
 */
public class Question55 {


    /**
     *
     * Example 1:
     *
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Example 2:
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     *              jump length is 0, which makes it impossible to reach the last index.
     *
     * 问题的本质是一个索引可以到达，每个先前的索引都必须是可达的。
     */
    public boolean canJump(int[] nums) {

        int reachable = 0;
        for (int i=0; i< nums.length; ++i) {
            if (i > reachable) {  //如果索引超过了最远可达索引,那么说明,当前索引是不可能到达的,后续的所有索引就更不可能了
                return false;
            }
            reachable = Math.max(reachable, i + nums[i]);  //reachable是最远可达索引
        }
        return true;
    }


    public static void main(String[] args) {
        Question55 question55 = new Question55();
        int[] nums = {3,2,1,0,4};
        System.out.println(question55.canJump(nums));
    }
}
