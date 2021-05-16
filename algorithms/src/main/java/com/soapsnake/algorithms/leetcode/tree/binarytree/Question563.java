package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * 
 * Created on 2020-11-08
 */
public class Question563 {

    //leetcode563
    public int findTilt(TreeNode root) {
        postOrder(root);
        return result;
    }
    int result = 0;
    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);
        result += Math.abs(left - right);
        return left + right + root.val;
    }
}
