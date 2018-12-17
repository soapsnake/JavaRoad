package com.soapsnake.algorithms.leetcode.binarytree;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-17 00:00
 */
public class Question108 {

    TreeNode root;
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        midTravel(nums, left, right);
        return root;
    }

    private TreeNode midTravel(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        System.out.println("mid = " + mid);
        root = new TreeNode(nums[mid]);
        if (mid == 0 || mid == nums.length - 1) {
            return root;
        }
        root.left = midTravel(nums, left, mid - 1);
        root.right = midTravel(nums, mid + 1, right);
        TreeNode.layerTravse(root);

        return root;
    }

    public static void main(String[] args) {
        Question108 question108 = new Question108();
        int[] tar = {-10,-3,0,5,9};
        TreeNode node = question108.sortedArrayToBST(tar);
        TreeNode.layerTravse(node);
    }
}
