package com.ld.leetcode;


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
        return root;
    }

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + (left > right ? left : right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node = solution.makeTree();
        System.out.println("max depth is: " + solution.maxDepth(node));
    }
}
