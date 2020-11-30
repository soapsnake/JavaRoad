package com.soapsnake.algorithms.leetcode.dp;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Question416 {

    public boolean canPartition(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        //所有数字总和必然是偶数,因为两个子数组的和相等
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;  //那么这里除2求得的数字一定是任一子数组的和
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        /**
         * dp transition:
         * Since the problem is a 0-1 backpack problem, we only have two choices which are take or not.
         * Thus in this problem, by using the sum value as the index of DP array, we transfer the problem to "whether
         * 因此在这个问题中,通过使用目标总和为dp的索引,我们可以把这个问题转换为:我们是否可以使用已经访问的
         * should we take the currently visited number into the sum or not".
         * To construct the DP recurrence, when we are visiting nums[i] and to find partition of sum j: if we do not take the nums[i],
         * then the current iteration does not make any difference on current DP value;
         * if we take the nums[i], then we need to find whether the (new_sum = j - nums[i]) can be constructed.
         * If any of this two construction can work, the partition of sum == j can be reached.
         */
        for (int left = 1; left <= nums.length; left++) {
            for (int right = volumn; right >= nums[left-1]; right--) {
                dp[right] = dp[right] || dp[right - nums[left-1]];
            }
        }
        return dp[volumn];
    }


    class FirstUnique {
        Set<Integer> uniq = new LinkedHashSet<>();
        Set<Integer> noUniq = new LinkedHashSet<>();

        public FirstUnique(int[] nums) {
            for (int n : nums) {
                this.add(n);
            }
        }

        public int showFirstUnique() {
            if (uniq.iterator().hasNext()) {
                return uniq.iterator().next();
            } else {
                return -1;
            }
        }

        public void add(int value) {
            if (noUniq.contains(value)) {
                return;
            }
            if (uniq.contains(value)) {
                noUniq.add(value);
                uniq.remove(value);
                return;
            }
            uniq.add(value);
        }
    }

    //滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        LinkedList<Integer> queue = new LinkedList<>();  //queue中放的是索引
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length ; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            if (i + 1 >= k) {
                res[i] = nums[queue.peekFirst()];  //队列头放的是最大值的索引
            }
        }
        return res;
    }
}
