package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is sy†mmetric:
 */
class Question101 {
    public static void main(String[] args) {
        TreeNode origin = TreeNode.makeNormalTree();
        System.out.println("======================================");
        Question101 question101 = new Question101();
        System.out.println(question101.isSymmetric(origin));
    }

    public boolean isSymmetric2(TreeNode root) {
        //todo 思路2,利用递归,入参为两个节点;
        return false;
    }

    //思路:层状遍历,每一层都是对称结构,可用左右指针碰撞算法校验每一层是否对称
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return this.layer(root);
    }

    private boolean layer(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<TreeNode> row = null;
        while (!queue.isEmpty()) {
            row = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null)
                    row.add(node);
            }
            int left = 0;
            int right = row.size() - 1;
            while (right > left) {
                if (row.get(left) == null && row.get(right) != null) {
                    return false;
                }
                if (row.get(left) != null && row.get(right) == null) {
                    return false;
                }
                if (row.get(left) == null && row.get(right) == null) {
                    right--;
                    left++;
                    continue;
                }
                if (row.get(left).val != row.get(right).val) {
                    return false;
                } else {
                    right--;
                    left++;
                }
            }
            for (TreeNode node : row) {
                if (node == null) continue;
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return true;
    }
}
