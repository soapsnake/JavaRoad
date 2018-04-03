package com.ld.leetcode;


import java.util.LinkedList;
import java.util.Queue;

class Question104 {
    public static void main(String[] args) {
        Question104 solution = new Question104();
        TreeNode node = TreeNode.makeTree();
        System.out.println("max depth is: " + solution.maxDepth(node));

        System.out.println("layerTraverse:");
        layerTravse(node);
    }

    //层状遍历二叉树
    public static void layerTravse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) {
            return;
        }

        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            System.out.println(current.val);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
            if (queue.contains(current.left) && queue.contains(current.right)) {
                System.out.println("本层结束:" + i++);
            }
        }
        System.out.println("本层结束:" + i);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + (left > right ? left : right);
    }
}

