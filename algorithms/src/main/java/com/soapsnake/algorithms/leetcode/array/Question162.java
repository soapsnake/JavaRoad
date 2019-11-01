package com.soapsnake.algorithms.leetcode.array;

public class Question162 {

    public static void main(String[] args) {
        Question162 question162 = new Question162();
        int[] nums = {1, 2, 3, 1};
        System.out.println(question162.findPeakElement(nums));
    }

    /**
     * //todo 本题见c++版本代码的注解
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 1 or 5
     */
    public int findPeakElement(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return 0;
        }

        int left = 0, right = N - 1;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return (left == N - 1 || nums[left] > nums[left + 1]) ? left : right;
    }
}
