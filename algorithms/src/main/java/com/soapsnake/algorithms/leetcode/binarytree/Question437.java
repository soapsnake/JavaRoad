package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Question437 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.makeNormalTreeFor437();
        Question437 question437 = new Question437();
        System.out.println(question437.pathSum(treeNode, 8));
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
        //我觉得这个解法完全没有问题,不知道为啥不算对,他妈的
        if (root == null) {
            return 0;
        }
        List<String> finalres = new ArrayList<>();
        this.treeDFS(0, root, sum, "", finalres, false);
        System.out.println(finalres);
        return finalres.size();
    }

    private void treeDFS(int temp, TreeNode root, int sum, String path, List<String> finalres, boolean start) {
        if (root == null) {
            return;
        }
        if (root.val + temp == sum) {
            path = path + root.val;
            finalres.add(path);
            return;
        }

        //如果还没开始求和,那么节点就有两种选择:作为第一个节点开始进行求和, 或者不开始求和,留给子类去做选择
        if (!start) {
            //本节点不作为开始节点
            this.treeDFS(temp, root.left, sum, path, finalres, false);
            this.treeDFS(temp, root.right, sum, path, finalres, false);

            //本节点作为开始节点
            temp = temp + root.val;
            path = path + root.val + "->";
            this.treeDFS(temp, root.left, sum, path, finalres, true);
            this.treeDFS(temp, root.right, sum, path, finalres, true);
        }else {
            //如果已经开始进行求和,那么这个节点就没有选择了,必须跟进
            temp = temp + root.val;
            path = path + root.val + "->";
            this.treeDFS(temp, root.left, sum, path, finalres, true);
            this.treeDFS(temp, root.right, sum, path, finalres, true);
        }
    }
}
