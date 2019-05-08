package com.soapsnake.algorithms.leetcode.array;

public class Question153 {

    /**
     * 升序数组旋转后的最小值
     * Example 1:
     * Input: [3,4,5,1,2]
     * Output: 1
     *
     * Example 2:
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     */
    //遍历傻逼向
    public int findMin(int[] nums) {
        int min = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }
        return min;
    }

    //二分高手向
    public int findMin2(int[] nums) {
        int min = nums[0];

        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (right > left) {
            int mid = (left + right) / 2;
            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left];  //为什么要是nums[left]了?
    }

}
