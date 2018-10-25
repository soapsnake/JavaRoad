package com.ld.leetcode.binarytree;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 */
public class Question100 {

    public static void main(String[] args) {
        Question100 question100 = new Question100();

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);


        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(3);
        node1.right = new TreeNode(2);

        System.out.println(question100.isSameTree(node, node1));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        boolean left = false;
        boolean right = false;
        if (p.val == q.val) {
            left = isSameTree(p.left, q.left);
            right = isSameTree(p.right, q.right);
        }
        return left && right;
    }
}
