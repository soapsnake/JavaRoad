package com.soapsnake.algorithms.leetcode.array;

/**
 *
 * Created on 2020-01-16
 */
public class Question376 {

    /**
     * Example 1:
     * Input: [1,7,4,9,2,5]
     * Output: 6
     * Explanation: The entire sequence is a wiggle sequence.
     * <p>
     * <p>
     * Example 2:
     * Input: [1,17,5,10,13,15,10,5,16,8]
     * Output: 7
     * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
     */
    public int wiggleMaxLength(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int k = 0;
        while (k < nums.length - 1) {
            if (nums[k] == nums[k + 1]) {
                k++;
            } else {
                break;
            }
        }
        if (k == nums.length - 1) {
            return 1;
        }
        int res = 2;  //这个地方是最容易错的
        boolean increase = nums[k] < nums[k + 1];
        for (int i = k + 1; i < nums.length - 1; i++) {
            if (increase && nums[i] > nums[i + 1]) {
                increase = !increase;
                res++;
            } else if (!increase && nums[i] < nums[i + 1]) {
                increase = !increase;
                res++;
            }
        }
        return res;
    }

    /**
     *  Dp版解法
     * For every position in the array, there are only three possible statuses for it.
     * up position, it means nums[i] > nums[i-1]
     * down position, it means nums[i] < nums[i-1]
     * equals to position, nums[i] == nums[i-1]
     * So we can use two arrays up[] and down[] to record the max wiggle sequence length so far at index i.
     * If nums[i] > nums[i-1], that means it wiggles up. the element before it must be a down position. so up[i] =
     * down[i-1] + 1; down[i] keeps the same with before.
     * If nums[i] < nums[i-1], that means it wiggles down. the element before it must be a up position. so down[i] =
     * up[i-1] + 1; up[i] keeps the same with before.
     * If nums[i] == nums[i-1], that means it will not change anything becasue it didn't wiggle at all. so both
     * down[i] and up[i] keep the same.
     *
     * In fact, we can reduce the space complexity to O(1), but current way is more easy to understanding.
     *
     * 核心思想是利用两个数组来分别保存原始数组中的上升序列和下降序列
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length == 0) return 0;
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                down = up + 1;   //这里省略了down数组,只用这个来表示down数组的最大长度
            } else if (nums[i] > nums[i - 1]) {
                up = down + 1;   //同down数组,这里的up只用来标识最大长度
            }
        }
        return Math.max(up, down);  //取两个数组的最大长度
    }

    public static void main(String[] args) {
        Question376 question376 = new Question376();
        int[] nums = {0, 0, 0};
        System.out.println(question376.wiggleMaxLength(nums));
    }
}