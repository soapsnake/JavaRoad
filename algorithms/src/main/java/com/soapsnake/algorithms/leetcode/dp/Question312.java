package com.soapsnake.algorithms.leetcode.dp;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-12-13
 */
public class Question312 {

    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return maxCoins(nums, 0, nums.length - 1, dp);
    }

    public int maxCoins(int[] nums, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int max = nums[start];
        for (int i = start; i <= end; i++) {
            int val = maxCoins(nums, start, i - 1, dp) +
                    get(nums, i) * get(nums, start - 1) * get(nums, end + 1) +
                    maxCoins(nums, i + 1, end, dp);

            max = Math.max(max, val);
        }
        dp[start][end] = max;
        return max;
    }

    public int get(int[] nums, int i) {
        if (i == -1 || i == nums.length) {
            return 1;
        }
        return nums[i];
    }

    //是否二叉平衡搜索树
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isValid(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }

    private boolean isValid(long minValue, long maxValue, TreeNode root) {
        if (root == null) {
            return false;
        }
        if (root.val < minValue || root.val > maxValue) {
            return false;
        }
        return isValid(minValue, root.val, root.left) && isValid(root.val, maxValue, root.right);
    }

}
