package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.HashMap;
import java.util.Map;

import com.soapsnake.algorithms.structures.tree.TreeNode;

public class Question337 {

    /**
     * 题目: 二叉树,最大路径和,限制条件,两个节点不能直接相连
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return robDfs(root, new HashMap<>());
    }

    //自顶向下,利用hashmap做缓存避免重复计算
    public int robDfs(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int val = 0;
        if (map.containsKey(root)) {
            return map.get(root);
        }
        if (root.left != null) {
            val += robDfs(root.left.left, map) + robDfs(root.left.right, map);
        }
        if (root.right != null) {
            val += robDfs(root.right.left, map) + robDfs(root.right.right, map);
        }
        //最大值分两种情况: 从root层开始算; 不从root层开始算
        int max =  Math.max(root.val + val, robDfs(root.left, map) + robDfs(root.right, map));
        map.put(root, max);
        return max;
    }


    //dp解法,有点难以理解
    public int rob2(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];
        //不从root层开始
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //从root层开始
        res[1] = root.val + left[0] + right[0];
        return res;
    }


    public static void main(String[] args) {

    }

}
