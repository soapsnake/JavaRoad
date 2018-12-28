package com.soapsnake.algorithms.leetcode.binarytree;

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
        //思路,把数组不断求中位数索引,传mid和数组不同部分进去
        TreeNode root = this.find(0, nums.length - 1, nums);
        return root;
    }

    private TreeNode find(int left, int right, int[] nums) {
        if (left > right) {  //递归终止条件
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);   //数组的中卫数作为父节点
        node.left = find(left, mid - 1, nums);
        node.right = find(mid + 1, right, nums);
        return node;
    }


    public static void main(String[] args) {
        Question108 question108 = new Question108();
        int[] tar = {-10,-3,0,5,9};
        TreeNode node = question108.sortedArrayToBST(tar);
        TreeNode.layerTravse(node);
    }
}
