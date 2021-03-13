package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 *
 * Created on 2020-06-07
 */
public class Question623 {

    //leetcode632
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode newroot = new TreeNode(v);
            newroot.left = root;
            return newroot;
        }

        dfs(root, v, d, 1);
        return root;
    }

    private void dfs(TreeNode root, int v, int d, int curDepth) {
        if (root == null) {
            return;
        }
        if (curDepth < d - 1) {
            dfs(root.left, v, d, curDepth + 1);
            dfs(root.right, v, d, curDepth + 1);
        } else {
            TreeNode tmpLeft = root.left;
            TreeNode tmpRight = root.right;
            root.left = new TreeNode(v);
            root.left.left = tmpLeft;
            root.right = new TreeNode(v);
            root.right.right = tmpRight;
        }
    }
}
