package com.ld.leetcode.binarytree;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 */
public class Question101 {
    public boolean isSymmetric(TreeNode root) {

        //TODO 这题还无解
        return false;
    }

    public static void main(String[] args) {
        TreeNode origin = TreeNode.makeTree();
        System.out.println("======================================");

        Question101 question101 = new Question101();
        System.out.println(question101.isSymmetric(origin));

    }
}
