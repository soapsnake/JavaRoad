package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 *
 * Created on 2020-04-29
 */
public class Question124 {

    //leetcode124
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MAX_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    int maxValue;
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
