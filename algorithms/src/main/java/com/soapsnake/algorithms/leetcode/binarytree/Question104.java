package com.soapsnake.algorithms.leetcode.binarytree;


import com.soapsnake.algorithms.structures.tree.TreeNode;

class Question104 {
    public static void main(String[] args) {
        Question104 solution = new Question104();
        TreeNode node = TreeNode.makeNormalTree();
        System.out.println("max depth is: " + solution.maxDepth(node));

        System.out.println("layerTraverse:");
        TreeNode.layerTravse(node);
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + (left > right ? left : right);
    }
}

