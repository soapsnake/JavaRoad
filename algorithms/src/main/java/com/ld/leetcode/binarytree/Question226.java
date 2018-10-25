package com.ld.leetcode.binarytree;

/**
 * Invert a binary tree.
 */
public class Question226 {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 3, 3, 4, 3, 3, 3, 4, 4, 4, 4, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 4, 3, 4, 4, 4, 3, 4, 3, 4, 3, 4, 4, 3, 3, 4, 3, 4, 3, 3, 3, 4, 3, 3};
        System.out.println(nums.length);

//        List<Integer> ls = new ArrayList<Integer>(Arrays.asList(nums));

        TreeNode old = TreeNode.makeTree();

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

}
