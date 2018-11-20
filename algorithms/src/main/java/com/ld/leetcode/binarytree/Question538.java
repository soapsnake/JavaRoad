package com.ld.leetcode.binarytree;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST
 * is changed to the original key plus sum of all keys greater than the original key in BST.
 */
class Question538 {

    public static void main(String[] args) {
        Question538 question538 = new Question538();
        TreeNode node = TreeNode.makeTree();
        TreeNode newTree = question538.convertBST(node);
        TreeNode.layerTravse(newTree);
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.right != null) {
            root.val += root.right.val;
        }
        if (root.left != null) {
            root.left.val += root.val;
        }

        int left = 0, right = 0;
        left = root.left == null ? 0 : root.left.val;
        right = root.right == null ? 0 : root.right.val;
        if (left > right) {
            TreeNode temp;
            temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        if (root.right != null) {
            convertBST(root.right);
        }
        if (root.left != null) {
            convertBST(root.left);
        }
        return root;
    }

}
