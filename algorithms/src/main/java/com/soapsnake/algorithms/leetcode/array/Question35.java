package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

import org.junit.Test;

class Question35 {

    //二分法思想
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    //leetcode35
    public int searchInsert2(int[] nums, int target) {
        if (null == nums) {
            return 0;
        }
        if (nums.length == 1) {
            return target > nums[0] ? 1 : 0;
        }
        if (target < nums[0]) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (target > nums[i] && i + 1 < nums.length && target < nums[i + 1]) {
                return i + 1;
            }
        }
        return nums.length;
    }

    public static void sortColors(int[] nums) {
        //nums中只有0,1,2,进行排序,不允许sort函数,不允许额外内存
        int zero = 0;
        int one = 0;
        int two = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero += 1;
            } else if (nums[i] == 1) {
                one += 1;
            } else {
                two += 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < zero) {
                nums[i] = 0;
            } else if (zero - 1 < i && i < zero + one) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

        public static void main(String[] args) {
//            int[] nums = new int[] {1, 2, 3, 4, 5, 7, 8, 10};
//            Question35 question35 = new Question35();
//            System.out.println(question35.searchInsert2(nums, 8));
            int[] nums1 = {2,0,2,1,1,0};
            sortColors(nums1);
            System.out.println(Arrays.toString(nums1));
        }
}
