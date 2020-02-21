package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.LinkedList;
import java.util.List;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-11 23:56
 */
public class Question113 {

    public static void main(String[] args) {

    }

    /**
     * Given the below binary tree and sum = 22,
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * Return:
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res, sum, new LinkedList<>());
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int sum, List<Integer> temp) {
        if (root == null) {
            return;
        }

        temp.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new LinkedList<>(temp));
        }

        dfs(root.left, res, sum, temp);
        dfs(root.right, res, sum, temp);
        temp.remove(temp.size() - 1);
    }

}
