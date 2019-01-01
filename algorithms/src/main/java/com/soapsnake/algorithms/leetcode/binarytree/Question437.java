package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Question437 {

    public static void main(String[] args) {
//        TreeNode treeNode = TreeNode.makeNormalTreeFor437();
        TreeNode treeNode = new TreeNode(1);

        Question437 question437 = new Question437();
        System.out.println(question437.pathSum(treeNode, 1));
    }

    /**
     * You are given a binary tree in which each node contains an integer value.
     * Find the number of paths that sum to a given value.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     * 求的是所有和为给定值的路径的总数量
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        //dfs
        //这个题难就难在路径上的节点必须是父子连续节点,不连续的节点不算
        if (root == null) {
            return 0;
        }
        List<String> res = new ArrayList<>();
        Map<String, TreeNode> preNode = new HashMap<>();
        preNode.put("", root);
        this.treeDFS(0, root, sum, "", res, preNode);
        System.out.println(res);
        return res.size();
    }

    private void treeDFS(int temp, TreeNode root, int sum, String path, List<String> res, Map<String, TreeNode> pre) {
        if (root == null) {
            return;
        }
        if (root.val + temp == sum) {
            if (pre.get(path).left == root || pre.get(path).right == root) {
                res.add(path + root.val);
                return;
            }
        }

        //略过本节点
        this.treeDFS(temp, root.left, sum, path, res, pre);
        this.treeDFS(temp, root.right, sum, path,res, pre);

        //加上本节点的值
        temp = temp + root.val;
        path = path + root.val + "->";
        pre.put(path, root);
        this.treeDFS(temp, root.left ,sum, path, res,  pre);
        this.treeDFS(temp, root.right ,sum, path, res, pre);
    }
}
