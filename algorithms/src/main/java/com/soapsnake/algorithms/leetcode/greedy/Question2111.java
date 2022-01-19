package com.soapsnake.algorithms.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2022-01-06
 */
public class Question2111 {

    public int kIncreasing(int[] arr, int k) {
        int ans = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> list = new ArrayList<>();
            int j = i;
            while (j < arr.length) {
                list.add(arr[j]);
                j += k;
            }
            Integer[] nums = list.toArray(new Integer[0]);
            int temp = maxLen(nums);
            ans += nums.length - temp;
        }
        return ans;
    }

    public int maxLen(Integer[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] >= d[len]) {
                d[++len] = nums[i];
            } else {
                int left = 1, right = len, pos = 0;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (d[mid] <= nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
