package com.soapsnake.algorithms.leetcode.binarytree;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-31 20:14
 */
public class Question112 {
    private boolean has = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        dfsTree(root,0, sum);
        return has;
    }

    private void dfsTree(TreeNode root, int step, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right ==null) {
            if (root.val + step == sum) {
                has = true;
                return;
            }
        }
        step += root.val;
        dfsTree(root.left, step, sum);
        dfsTree(root.right, step, sum);
    }


    public static void main(String[] args) {

    }
}
