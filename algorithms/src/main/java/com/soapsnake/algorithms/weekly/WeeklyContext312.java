package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-09-25
 * JavaRoad
 */
public class WeeklyContext312 {

    public static void main(String[] args) {
        WeeklyContext312 weeklyContest312 = new WeeklyContext312();
        System.out.println();


        int[] nums1 = {2,1,1,1,3,4,1};
        int[] nums2 = {2,1,1,2};
        int[] nums3 = {878724,201541,179099,98437,35765,327555,475851,598885,849470,943442};
        int[] nums4 = {440043,276285,336957};
        int k1 = 2;
        int k4 = 1;
        int k2 = 2;
        int k3 = 4;

        System.out.println(weeklyContest312.goodIndices2(nums1, k1));  //should [2, 3]
    }


    public List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (isgood(i, nums, k)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isgood(int i, int[] nums, int k) {
        int n = nums.length;
        if (i - k < 0) {
            return false;
        }
        if (i + k >= n) {
            return false;
        }
        int c = 2;
        int preLeft = nums[i - 1];
        int preRight = nums[i + 1];
        while (c <= k ) {
            if (nums[i - c] < preLeft) {
                return false;
            }
            preLeft = nums[i - c];
            if (nums[i + c] < preRight) {
                return false;
            }
            preRight = nums[i + c];
            c++;
        }
        return true;
    }

    public List<Integer> goodIndices2(int[] nums, int k) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        //左边必须是下降的
        int[] left = new int[n];
        Arrays.fill(left, 1);
        //右边必须是上升的
        int[] right = new int[n];
        Arrays.fill(right , 1);
        //i左侧的元素必须是下降的
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        for (int i = 0; i < n; i ++) {
            if (i - k < 0 || i + k >= n) {
                continue;
            }
            if (left[i - 1] >= k && right[i + 1] >= k) {
                res.add(i);
            }
        }
        return res;
    }

}
