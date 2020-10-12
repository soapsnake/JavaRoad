package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-08 14:34
 */
public class Question16 {

    public static void main(String[] args) {
        Question16 question16 = new Question16();
        int[] nums = {-1, 2, 1, -4};
        System.out.println(question16.threeSumClosest(nums, 1));
    }

    /**
     * Given array nums = [-1, 2, 1, -4], and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target) {

        //3sum,不过这里要求的是与target最接近的数,思路其实和3sum完全一致
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int gap = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (right > left) {
                int temp = nums[right] + nums[left];
                if (temp == sum) {
                    return target;  //和target最接近的就是等于target的
                } else if (temp > sum) {
                    right--;
                    int abs = Math.abs(temp + nums[i] - target);
                    if (abs < gap) {
                        gap = abs;
                        res = temp + nums[i];
                    }
                } else {
                    int abs = Math.abs(temp + nums[i] - target);
                    if (abs < gap) {
                        gap = abs;
                        res = temp + nums[i];
                    }
                    left++;
                }
            }
        }
        return res;
    }
}
