package com.ld.leetcode;


import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      int maxDepth=0;
 }

class Solution {
    /**
     * [3,9,20,null,null,15,7]
     *         3
     *        /\
     *      9   20
     *         / \
     *      15   7
     *          /\
     *        8   12
     *           / \
     *         10   22
     *        /  \
     *      4     14
     * @return
     */
    public TreeNode makeTree(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(12);
        root.right.right.left = new TreeNode(8);
        root.right.right.right.right = new TreeNode(22);
        root.right.right.right.left = new TreeNode(10);
        root.right.right.right.left.left = new TreeNode(4);
        root.right.right.right.left.right = new TreeNode(14);
        return root;
    }

    public int maxDepth(TreeNode node) {
        if (node == null){
            return 0;
        }
        if (node.left == null && node.right == null){
            return 1;
        }

        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return 1 + (left > right ? left : right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node = solution.makeTree();
        System.out.println("max depth is: " + solution.maxDepth(node));

        System.out.println("layerTraverse:");
        layerTravse(node);
    }

    //层状遍历二叉树
    public static void layerTravse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null){
            return;
        }

        queue.add(root);
        int i=1;
        while (!queue.isEmpty()){
            TreeNode current = queue.poll();

            System.out.println(current.val);

            if (current.left != null){
                queue.add(current.left);
            }
            if (current.right != null){
                queue.add(current.right);
            }
            if (queue.contains(current.left) && queue.contains(current.right)){
                System.out.println("本层结束:" + i++);
            }
        }
        System.out.println("本层结束:"+ i);
    }

}
