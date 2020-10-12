package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-24 00:16
 */
public class Question219 {

    public static void main(String[] args) {
        Question219 question219 = new Question219();
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println(question219.containsNearbyDuplicate2(nums, k));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    if (Math.abs(i - j) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //todo 思想:把数字缓存起来,k步内验证是否有重,k步后把该数字清除
    public boolean containsNearbyDuplicate2(int[] nums, int k) {


        return false;
    }
}
