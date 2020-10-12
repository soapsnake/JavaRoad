package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Question222 {

    private List<TreeNode> nodes;

    /**
     * Example:
     * <p>
     * Input:
     * 1
     * / \
     * 2   3
     * / \  /
     * 4  5 6
     * <p>
     * Output: 6
     */
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        nodes = new ArrayList<>();
        dfs(root);
        return nodes.size();
    }

    private void dfs(TreeNode root) {
        nodes.add(root);
        if (root.left != null)
            dfs(root.left);
        if (root.right != null)
            dfs(root.right);
    }
}
