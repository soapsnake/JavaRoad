package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-20 18:53
 */
public class Question129 {


    int sum = 0;

    public static void main(String[] args) {
        Question129 question129 = new Question129();
        TreeNode root = TreeNode.makeNormalTreeFor129();
        System.out.println(question129.sumNumbers(root));
    }

    /**
     * Example:
     * Input: [1,2,3]
     * 1
     * / \
     * 2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, "");
        return sum;
    }

    private void dfs(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            String total = path + root.val;
            int totalInt = Integer.valueOf(total);
            sum += totalInt;
            return;
        }
        dfs(root.left, path + root.val);
        dfs(root.right, path + root.val);
    }
}
