package com.ld.leetcode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 */
public class Question101 {
    public boolean isSymmetric(TreeNode root) {
        TreeNode revertRoot = revertTree(root);
        return traverseTree(root , revertRoot);
    }

    private boolean traverseTree(TreeNode root, TreeNode revertRoot) {
        boolean res = true;

        if (root == null){
            return revertRoot == null;
        }

        if (root.val != revertRoot.val){
            res =  false;
        }
        boolean resleft = traverseTree(root.left, revertRoot.left);
        boolean resright = traverseTree(root.right, revertRoot.right);
        return res;
    }

    private TreeNode revertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode tmp;
        tmp =  root.left;
        root.left = root.right;
        root.right = tmp;

        revertTree(root.left);
        revertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode origin = TreeNode.makeTree();
        TreeNode.layerTravse(origin);

        System.out.println("======================================");

        Question101 question101 = new Question101();
        TreeNode revese = question101.revertTree(origin);
        TreeNode.layerTravse(revese);

        System.out.println(question101.isSymmetric(origin));

    }
}
