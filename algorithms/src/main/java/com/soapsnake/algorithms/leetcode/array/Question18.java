package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-09 20:33
 */
public class Question18 {

    public static void main(String[] args) {
        Question18 question18 = new Question18();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(question18.fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);  //关键点
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp1 = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int temp2 = temp1 - nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                while (right > left) {
                    if (nums[right] + nums[left] == temp2) {
                        List<Integer> inter = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        if (!res.contains(inter)) {  //防重复
                            res.add(inter);
                        }
                        right--;
                        left++;
                    } else if (nums[right] + nums[left] > temp2) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
