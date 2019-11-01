package com.soapsnake.algorithms.leetcode.array;

import java.util.TreeSet;

public class Question220 {

    /**
     * Example 1:
     * Input: nums = [1,2,3,1], k = 3, t = 0
     * Output: true
     *
     * Example 2:
     * Input: nums = [1,0,1,1], k = 1, t = 2
     * Output: true
     *
     * Example 3:
     * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
     * Output: false
     */

    public static void main(String[] args) {
        String string = "ce";
        System.out.println(string.length());

        String string2 = "测";
        System.out.println(string2.length());

    }

    /**
     * 这道题是真的难,有资格hard难度
     * This problem requires to maintain a window of size k of the previous values that can be queried for value ranges.
     * The best data structure to do that is Binary Search Tree.
     * As a result maintaining the tree of size k will result in time complexity O(N lg K).
     * In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple queries can be executed both of time complexity O(lg K)
     * Here is the whole solution using TreeMap.
     * <p>
     * 这道题用到了二叉平衡树,也就是java里面的TreeSet,需要先搞懂里面的api的含义
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //索引的最大差值不能超过k, 值最大差值不能超过t

        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }

        final TreeSet<Integer> values = new TreeSet<>();
        for (int ind = 0; ind < nums.length; ind++) {

            final Integer floor = values.floor(nums[ind] + t);
            final Integer ceil = values.ceiling(nums[ind] - t);
            if ((floor != null && floor >= nums[ind])
                    || (ceil != null && ceil <= nums[ind])) {
                return true;
            }

            values.add(nums[ind]);
            if (ind >= k) {
                values.remove(nums[ind - k]);
            }
        }

        return false;
    }
}
