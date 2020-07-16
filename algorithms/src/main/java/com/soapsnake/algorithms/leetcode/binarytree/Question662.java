package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * }
 * Created on 2020-07-09
 */
public class Question662 {

    //leetcode662
    //总体思路类似优先级队列中的大顶/小顶堆,把树塞到一个数组当中,由于是二叉树,所以root * 2就是子节点
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end) {
        if (root == null)
            return 0;
        if (start.size() == level) {
            start.add(order);
            end.add(order);
        } else
            end.set(level, order);
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * order, start, end);
        int right = dfs(root.right, level + 1, 2 * order + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }
}
