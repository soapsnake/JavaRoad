package com.ld.leetcode.array;

class Question53 {

    public static void main(String[] args) {
        Question53 question53 = new Question53();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = new int[]{-2,-1};
        int res = question53.maxSubArray(nums);
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
    public int maxSubArray2(int[] A) {
        int maxSoFar = A[0], maxEndingHere = A[0];
        for (int i = 1; i < A.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

}
