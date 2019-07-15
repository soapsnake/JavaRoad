package com.soapsnake.algorithms.leetcode.array;

public class Question307 {

    public static void main(String[] args) {
        int[] nums = {};
        NumArray numArray = new NumArray(nums);
    }


    static class NumArray {
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int i, int val) {
            this.nums[i] = val;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += this.nums[k];
            }
            return sum;
        }
    }
}
