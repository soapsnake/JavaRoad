package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/1 21:51
 */
public class Question453 {


    public static void main(String[] args) {
        Question453 question453 = new Question453();
        int[] tar = {1, 1, 712937829};
        System.out.println(question453.minMoves(tar));
    }

    /**
     * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal,
     * where a move is incrementing n - 1 elements by 1.
     * <p>
     * 把n - 1个元素进行加1操作,反过来,其实就是把某一个元素进行减1操作
     */
    public int minMoves(int[] nums) {

        //其实只需要找出来最小的数字就行了,没必要做排序
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }
}
