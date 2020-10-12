package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 *
 * Created on 2020-03-10
 */
public class Question462 {

    /**
     * Example:
     * Input:
     * [1,2,3]
     *
     * Output:
     * 2
     *
     * Explanation:
     * Only two moves are needed (remember each move increments or decrements one element):
     * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     */
    /**
     * we sort the nums array, assume all nums will finally be modified to some value C in the middle of the array,
     * so we need to sum the diff of all nums larger than C, and all nums less than C,
     * so it transforms to: nums[n]-C+nums[n-1]-C....+(C-nums[mid-1]+C-nums[mid-2]+...C=nums[0])=nums[n]-nums[0]+nums[n-1]-nums[1]+....
     数组排序后,把数组所有的数字都通过+1和-1变更到中位数值
     */
    //leetcode462
    public int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }
}
