package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * Invert a binary tree.
 */
class Question226 {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 3, 3, 4, 3, 3, 3, 4, 4, 4, 4, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 4, 3, 4, 4, 4, 3, 4, 3, 4, 3, 4, 4, 3, 3, 4, 3, 4, 3, 3, 3, 4, 3, 3};
        System.out.println(nums.length);

//        List<Integer> ls = new ArrayList<Integer>(Arrays.asList(nums));

        TreeNode old = TreeNode.makeNormalTree();

        Question226 question226 = new Question226();
        TreeNode newTree = question226.invertTree(old);

        TreeNode.layerTravse(newTree);


    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode inverTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        inverTree2(root.left);
        inverTree2(root.right);
        return root;
    }

}
