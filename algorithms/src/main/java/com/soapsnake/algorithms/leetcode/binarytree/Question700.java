package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * Given the root node of a binary search tree (BST) and a value.
 * You need to find the node in the BST that the node's value equals the given value.
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 */
class Question700 {

    public static void main(String[] args) {

        Question700 question700 = new Question700();
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(7);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode.layerTravse(question700.searchBST(root, 2));
    }

    //递归版本实现
    public TreeNode searchBST(TreeNode root, int val) {
        if (null == root) {
            return null;
        }
        if (val == root.val) {
            return root;
        } else if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    //todo 用遍历实现一次
    public TreeNode searchBST2(TreeNode root, int val) {

        return null;
    }

}
