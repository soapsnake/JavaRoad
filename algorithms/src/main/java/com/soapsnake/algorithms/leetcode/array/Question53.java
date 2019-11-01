package com.soapsnake.algorithms.leetcode.array;

class Question53 {

    public static void main(String[] args) {
        Question53 question53 = new Question53();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = new int[]{-2,-1};
        int res = question53.maxSubArr3(nums);
        System.out.println(res);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        int temp = nums[0];
        int maxTemp = nums[0];
        for (int j = 0; j < nums.length; j++) {
            for (int i = j; i < nums.length; i++) {
                max += nums[i];
                if (max > temp) {
                    temp = max;
                }
            }
            if (temp > maxTemp) {
                maxTemp = temp;
            }
            max = 0;
        }
        return maxTemp;
    }

    //动态规划解法,论文见(Sep. 1984 Vol. 27 No. 9 Communications of the ACM P885)
    public int maxSubArray2(int[] nums) {
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public int maxSubArr3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (total < 0) {
                total = 0;
                if (nums[i] > max) {
                    max = nums[i];
                }
            } else {
                if (total > max) {
                    max = total;
                }
            }
        }
        return max;
    }

}
