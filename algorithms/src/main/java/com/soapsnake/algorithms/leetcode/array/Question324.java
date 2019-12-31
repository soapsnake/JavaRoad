package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question324 {

    /**
     * 题目要求o(n)复杂度,不使用额外内存
     * Example 1:
     * Input: nums = [1, 5, 1, 1, 6, 4]
     * Output: One possible answer is [1, 4, 1, 5, 1, 6].
     *
     *
     *
     * Example 2:
     * Input: nums = [1, 3, 2, 2, 3, 1]
     * Output: One possible answer is [2, 3, 1, 3, 1, 2].
     *
     * 解法解释:https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
     */
    public void wiggleSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int leftIndex, int rightIndex) {
        int temp = nums[leftIndex];
        nums[leftIndex] = nums[rightIndex];
        nums[rightIndex] = temp;
    }

    public static void main(String[] args) {
        Question324 question324 = new Question324();
        int[] nums = {3, 2, 5, 1, 8, 6};
        int left = 0;
        int right = 3;
        System.out.println(question324.findKthLargest(nums, 3));
        System.out.println(Arrays.toString(nums));
    }

    //找数组中第k大的数字,如果i是nums的中位索引,那么该函数处理过后,nums将会形成i以前的数字都比i小,i以后的数字都比i大
    private int findKthLargest(int[] nums, int i) {
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            System.out.println("right = " + right + " left= " + left);
            while (nums[left] <= nums[i]) {
                left++;
            }
            while (nums[right] >= nums[i]) {
                right--;
            }
            if (right >= left) {
                swap(nums, right, left);
                left++;
                right--;
            }
        }
        return nums[i];
    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
}
