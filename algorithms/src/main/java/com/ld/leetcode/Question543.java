package com.ld.leetcode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 */
public class Question543 {

    //解法:借助树的最大深度算法,每一次递归的过程中刷新max的值,直到求的最大值
    public int diameterOfBinaryTree(TreeNode root) {
         maxDepth(root);
        return max;
    }

    int max = 0;
    public int maxDepth(TreeNode node){
        if (node == null){
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);

        //关键:每一次都需要比较最大距离
        max = Math.max(max, left + right);
        return (left > right ? left : right) + 1;
    }

    public static void main(String[] args) {
        Question543 question543 = new Question543();
        TreeNode treeNode = TreeNode.makeTree();
        int max = question543.diameterOfBinaryTree(treeNode);
        System.out.println(max);
    }
}
