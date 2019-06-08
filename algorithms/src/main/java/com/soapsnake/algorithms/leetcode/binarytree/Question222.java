package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Question222 {

    /**
     * Example:
     *
     * Input:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     *
     * Output: 6
     */
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        nodes = new ArrayList<>();
        dfs(root);
        return nodes.size();
    }
    private List<TreeNode> nodes;
    private void dfs(TreeNode root) {
        nodes.add(root);
        if (root.left != null)
            dfs(root.left);
        if (root.right != null)
            dfs(root.right);
    }
}
