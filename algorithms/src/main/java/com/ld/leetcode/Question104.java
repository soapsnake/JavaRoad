package com.ld.leetcode;


import static com.ld.leetcode.TreeNode.layerTravse;

class Question104 {
    public static void main(String[] args) {
        Question104 solution = new Question104();
        TreeNode node = TreeNode.makeTree();
        System.out.println("max depth is: " + solution.maxDepth(node));

        System.out.println("layerTraverse:");
        layerTravse(node);
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

