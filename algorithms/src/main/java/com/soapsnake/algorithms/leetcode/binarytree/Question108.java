package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-17 00:00
 */
public class Question108 {

    /**
     * 排序数组转平衡二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
       if (nums == null || nums.length == 0) {
           return null;
       }
       int left = 0;
       int right = nums.length - 1;
       return find(left, right, nums);
    }

    private TreeNode find(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = find(left, mid - 1, nums);
        root.right = find(mid + 1, right, nums);
        return root;
    }


    public static void main(String[] args) {
        Question108 question108 = new Question108();
        int[] tar = {-10,-3,0,5,9};
        TreeNode node = question108.sortedArrayToBST(tar);
        TreeNode.layerTravse(node);
    }
}
