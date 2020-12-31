package com.soapsnake.algorithms.leetcode.tree.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-20 22:36
 */
public class Question110 {

    public static void main(String[] args) {
        TreeNode root = TreeNode.makeNormalTreeFor110();
        Question110 question110 = new Question110();
        System.out.println(question110.isBalanced(root));

        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);
//        root1.right.right.right = new TreeNode(4);
        System.out.println(question110.isBalanced(root1));

    }

    /**
     * 解法的思路是对的,但是,由于特殊情况,这个答案过不了检查
     * https://leetcode.com/problems/balanced-binary-tree/discuss/36042/Two-different-definitions-of-balanced-binary-tree-result-in-two-different-judgments
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<List<TreeNode>> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).add(root);
        res.add(Arrays.asList(root));
        while (!queue.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            if (!temp.isEmpty()) {
                res.add(temp);
            }
            for (TreeNode node : temp) {
                ((LinkedList<TreeNode>) queue).add(node);
            }
        }
        if (res.size() <= 2) {
            return true;
        }
        for (int i = res.size() - 3; i >= 0; i--) {
            for (TreeNode node : res.get(i)) {
                if (node.left == null || node.right == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return isBalance;
        }
        this.dfsMax(root);
        return isBalance;
    }
    boolean isBalance = true;
    private int dfsMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = dfsMax(root.left);
        int maxRight = dfsMax(root.right);
        if (Math.abs(maxLeft - maxRight) > 1) {
            isBalance = false;
        }
        return Math.max(maxLeft, maxRight) + 1;
    }


}
