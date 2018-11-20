package com.ld.leetcode.array;

/**
 * @author soapsnake
 * @date 2018/11/17
 */
class Question485 {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int consecu = 1;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 1) {
                        consecu++;
                    } else {
                        break;
                    }
                }
                max = Math.max(max, consecu);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Question485 question485 = new Question485();
        int[] nums = {1,1,0, 1, 1, 1, 1};
        System.out.println(question485.findMaxConsecutiveOnes(nums));
    }
}
