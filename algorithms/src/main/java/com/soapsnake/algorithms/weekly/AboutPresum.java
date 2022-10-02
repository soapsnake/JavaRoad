package com.soapsnake.algorithms.weekly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created on 2022-01-19
 * <p>
 * 前缀和
 */
public class AboutPresum {

    public static void main(String[] args) {
        AboutPresum aboutPresum = new AboutPresum();
        int[] arr = {1, 3, 4, 2, 6, 8};
        //int[] arr = {0, 0, 0, 0};
        System.out.println(Arrays.toString(aboutPresum.findOriginalArray(arr)));
    }

    //1685
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int sum = 0;
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }
        //计算每个数的差绝对值之和
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int leftTotal = (i + 1) * nums[i] - preSum[i];
            int rightTotal = preSum[nums.length - 1] - preSum[i] - nums[i] * (nums.length - 1 - i);
            res[i] = leftTotal + rightTotal;
        }
        return res;
    }

    public long[] getDistances(int[] arr) {
        Map<Integer, long[]> map = new HashMap<>();   //value -> preSum, count
        long[] res = new long[arr.length];
        long[] presum = new long[arr.length];
        long[] postsum = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                long[] temp = map.get(arr[i]);
                presum[i] = (long) i * temp[1] - temp[0];
                temp[0] += i;
                temp[1]++;
            } else {
                map.put(arr[i], new long[]{i, 1});
            }
        }
        map.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (map.containsKey(arr[i])) {
                long[] temp = map.get(arr[i]);
                postsum[i] = temp[0] - (long) i * temp[1];
                temp[0] += i;
                temp[1]++;
            } else {
                map.put(arr[i], new long[]{i, 1});
            }
        }
        for (int i = 0; i < arr.length; i++) {
            res[i] = presum[i] + postsum[i];
        }
        return res;
    }

    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n == 0) {
            return new int[0];
        }
        if (n % 2 == 1) {
            return new int[0];
        }
        int[] res = new int[n / 2];
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            count.merge(changed[i], 1, Integer::sum);
        }
        int temp = 0;
        List<Integer> list = new ArrayList<>(count.keySet());
        Collections.sort(list);
        for (Integer num : list) {
            if (count.get(num) > count.getOrDefault(num + num, 0)) {
                return new int[0];
            }

            for (int i = 0; i < count.get(num); i++) {
                res[temp] = num;
                temp++;
                count.put(num + num, count.get(num + num) - 1);
            }
        }
        return res;
    }

    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] res = new int[n / 2];
        //查询对应高位存在
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[0];
            if (diff == 0 || diff % 2 != 0) {
                continue;
            }
            int index = 0;
            queue.clear();
            for (int num : nums) {
                if (!queue.isEmpty() && queue.peek() == num) {
                    queue.poll();
                    continue;
                }
                if (index == n / 2) {
                    break;
                }
                res[index++] = num + diff / 2;
                queue.offer(num + diff);
            }
            if (queue.isEmpty()) break;
        }
        return res;
    }

    @Test
    public void testRecoverArray() {
        AboutPresum aboutPresum = new AboutPresum();
        int[] nums = {11, 6, 3, 4, 8, 7, 8, 7, 9, 8, 9, 10, 10, 2, 1, 9};
        System.out.println(Arrays.toString(aboutPresum.recoverArray(nums)));
    }

    //1982, 此题事实上是leetcode里面最难得几道题之一
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int m = sums.length;
        int[] res = new int[n], left = new int[m / 2], right = new int[m / 2];
        for (int i = 0; i < n; ++i) {
            int diff = sums[1] - sums[0], hasZero = 0, p = -1, q = -1, k = 0;
            for (int j = 0; j < m; ++j) {
                if (k <= q && right[k] == sums[j]) k++;
                else {
                    if (0 == sums[j]) hasZero = 1;
                    left[++p] = sums[j];
                    right[++q] = sums[j] + diff;
                }
            }
            if (1 == hasZero) {
                res[i] = diff;
                sums = left;
            } else {
                res[i] = -diff;
                sums = right;
            }
            m /= 2;
        }
        return res;
    }

}
