package com.soapsnake.algorithms.weekly;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-11-06
 * JavaRoad
 */
public class WeeklyContext318 {

    public static void main(String[] args) {
        WeeklyContext318 w = new WeeklyContext318();
        System.out.println(w);

        int[] nums = {9,9,9,1,2,3};
        int k = 3;
        System.out.println(w.maximumSubarraySum(nums, k));   //should 12
    }

    public int[] applyOperations(int[] nums) {
        // nums非负
        int n = nums.length;
        int[] res = new int[n];
        int index = 0;
        for (int i = 0; i < n ; i++) {
            if (i == n - 1) {
              if (nums[i] != 0) {
                  res[index++] = nums[i];
              }
            } else {
                if (nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                    if (nums[i] != 0) {
                        res[index++] = nums[i];
                    }
                } else {
                    if (nums[i] != 0) {
                        res[index++] = nums[i];
                    }
                }
            }
        }
        return res;
    }

    public long maximumSubarraySum(int[] nums, int k) {
        //1. 连续 2.长度是k，3.自数组元素各不相同， 4.返回最大和   5.没有合条件的返回0
        //滑动窗口啊
        int n = nums.length;
        //r - l + 1 = k;
        int l = 0, r = 0;
        long windowSum = 0;
        long res = 0L;
        LinkedList<String> window = new LinkedList<>();
        boolean flag = true;
        for (; r < n; r++) {
            if (r < k - 1) {
                windowSum += nums[r];
                if (!window.add(String.valueOf(nums[r]))) {
                    flag = false;
                }
            } else if (r == k - 1) {
                windowSum += nums[r];
                if (window.contains(String.valueOf(nums[r]))) {
                    flag = false;
                }
                window.add(String.valueOf(nums[r]));
                if (flag) {
                    System.out.println("windowSum1 = " + windowSum);
                    if (!hasDup(window)) {
                        res = Math.max(windowSum, res);
                    }
                }
                window.remove(String.valueOf(nums[l]));
                windowSum -= nums[l];
                l++;
            } else {
                windowSum += nums[r];
                if (window.contains(String.valueOf(nums[r]))) {
                    //bunengyong
                } else {
                    System.out.println("windowSum2 = " + windowSum);
                    if (!hasDup(window)) {
                        res = Math.max(windowSum, res);
                    }
                }
                window.add(String.valueOf(nums[r]));
                windowSum -= nums[l];
                window.remove(String.valueOf(nums[l]));
                l++;
            }
        }
        return res;
    }

    private boolean hasDup(LinkedList<String> window) {
        boolean res = false;
        Set<String> set = new HashSet<>();
        for (String s : window ){
            if (!set.add(s)) {
                return true;
            }
        }
        return res;
    }
}
