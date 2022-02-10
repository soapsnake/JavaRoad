package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *xxxx
 * Created on 2022-01-30
 */
public class WeeklyContest278 {


    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        int index = -2;
        int temp = original;
        int prevIndex = -2;
        while (index != -1) {
            index = this.find(nums, temp);
            if (index == -1 && temp == original) {
                return original;
            }
            temp *= 2;
            if (index != -1) {
                prevIndex = index;
            }
        }
        return nums[prevIndex] * 2;
    }

    private int find(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        WeeklyContest278 weeklyContest = new WeeklyContest278();
        int[] nums = {0};
        System.out.println(weeklyContest.maxScoreIndices(nums));
    }

    public List<Integer> maxScoreIndices(int[] nums) {
        //分左右,分别求值,再求和,
        int zeroCount = 0;
        int oneCount = 0;
        if (nums.length == 1) {
            if (nums[0] == 0) {
                return Arrays.asList(1);
            } else {
                return Arrays.asList(0);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (nums[i] == 1) {
                oneCount++;
            }
        }
        int total0 = 0;
        int total1 = 0;
        int sum = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();  //total -> list(index)
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            if (nums[i] == 0) {
                total0++;
            } else {
                total1++;
            }
            if (i == 0) {
                temp = oneCount;
            } else if (i == nums.length - 1) {
                temp = zeroCount;
            } else {
                int left1 = oneCount - total1;
                temp = total0 + left1;
            }

            List<Integer> indexs = map.getOrDefault(temp, new ArrayList<>());
            if (i == 0) {
                indexs.add(0);
            } else if (i == nums.length - 1) {
                indexs.add(i + 1);
            } else {
                indexs.add(i + 1);
            }
            map.put(temp, indexs);
            sum = Math.max(sum, temp);
        }
        //System.out.println(map);
        return map.get(sum);
    }

    private int cal(int[] nums, int i, int i1, int i2) {
        int count = 0;
        for (int j = i; j < i1; j++) {
            if (nums[j] == i2) {
                count++;
            }
        }
        return count;
    }

}
