package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Question230 {

    int tar = 0;

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list, k);
        return tar;
    }

    private void dfs(TreeNode root, List<Integer> list, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, list, k);
        list.add(root.val);
        if (list.size() == k) {
            tar = root.val;
        }
        dfs(root.right, list, k);
    }
}
