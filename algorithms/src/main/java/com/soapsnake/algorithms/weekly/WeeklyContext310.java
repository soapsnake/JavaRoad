package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-09-11
 * JavaRoad
 */
public class WeeklyContext310 {

    public int lengthOfLIS(int[] nums, int k) {
        //邻居元素差值不超过k
        int n = nums.length;
        int[] q = new int[n];
        int len = 0;
        q[len++] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > q[len - 1] && nums[i] - q[len - 1] <= k) {
                q[len++] = nums[i];
            } else {
                int index = binerSearch(q, 0, len - 1, nums[i], k);
                q[index] = nums[i];
            }
        }
        return len;
    }

    private static int binerSearch(int[] arr, int l, int r, int tar, int k) {
        while (l < r) {
            int mid = l + (r - l ) / 2;
            if (arr[mid] <  tar)  {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        WeeklyContext310 weeklyContext310= new WeeklyContext310();
        int[][] intervals = {{441459,446342},{801308,840640},{871890,963447},{228525,336985},{807945,946787},{479815,507766},{693292,944029},{751962,821744}};
        System.out.println(weeklyContext310.minGroups(intervals));  //should3
    }

    public int minGroups(int[][] intervals) {
        //1.可以乱序 2.闭区间,边界相交
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(intervals, 0, memo);
    }

    public int dfs(int[][] intervals,int cur, int[] memo) {
        if (cur == intervals.length - 1) {
            return 0;
        }
        if (memo[cur] != -1) {
            return memo[cur];
        }
        //选当前,和不选当前,的最小值
        int nextIdx = cur + 1;
        for (; nextIdx < intervals.length; nextIdx++ ){
            if (intervals[nextIdx][0] > intervals[cur][0] && intervals[nextIdx][1] > intervals[cur][1]) {
                break;
            }
        }
        return 0;
    }
}
