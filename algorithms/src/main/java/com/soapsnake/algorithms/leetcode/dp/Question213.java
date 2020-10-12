package com.soapsnake.algorithms.leetcode.dp;

public class Question213 {

    public static void main(String[] args) {
        Question213 question213 = new Question213();
        int[] nums = {1, 2, 1, 1};
        System.out.println(question213.rob(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length < 2)
            return nums[0];

        int[] startFromFirstHouse = new int[nums.length + 1];  //该数组用来存从0开始的结果
        int[] startFromSecondHouse = new int[nums.length + 1];  //该数组用来存从1开始的结果

        startFromFirstHouse[0] = 0;
        startFromFirstHouse[1] = nums[0];   //后续2或者3
        startFromSecondHouse[0] = 0;
        startFromSecondHouse[1] = 0;     //后续3或者4

        for (int i = 2; i <= nums.length; i++) {
            startFromFirstHouse[i] = Math.max(startFromFirstHouse[i - 1], startFromFirstHouse[i - 2] + nums[i - 1]);
            startFromSecondHouse[i] = Math.max(startFromSecondHouse[i - 1], startFromSecondHouse[i - 2] + nums[i - 1]);
        }

        return Math.max(startFromFirstHouse[nums.length - 1], startFromSecondHouse[nums.length]);
    }


}
