package com.ld.leetcode.array;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class Question15 {

    public static void main(String[] args) {
        Question15 question15 = new Question15();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(question15.threeSum(nums));
    }

    //找出所有可能的组合
    public List<List<Integer>> threeSum(int[] nums) {
        //TODO 还需debug然后自己动手写一遍
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            //这个条件好像是为了防止重复的
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];    //nums[lo] + nums[hi] = - nums[i]
                while (lo < hi) {   //类似快排的左右指针碰撞时条件终止
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;  //奇怪了,这里不会出现重复解吗?哦不会,因为只要nums[lo] == nums[lo+1],就会进来循环lo的值就会增加,
                            //当结束这个while循环时这里的lo肯定和上次的是不一样的了
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {     //因为nums排序过,所以如果值过小,只需要向右移动左指针
                        lo++;
                    } else if (nums[lo] + nums[hi] > sum) { //因为nums排序过,所以如果值过大,只需要左移动右指针
                        hi--;
                    }
                }
            }
        }
        return res;
    }

}
