package com.ld.leetcode;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
      int depth=1;
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

    public int scanNode(TreeNode node){
       if (node == null){
           return 0;
       }

       node.depth = 1;
       maxDepth(node);
        System.out.println(node.depth);
       return node.depth;
    }

    public void maxDepth(TreeNode root) {
        if (root == null){
            return;
        }
        System.out.println("正在遍历的节点是:--> " + root.val);

        if (root.left != null){
            root.depth++;
            maxDepth(root.left);
        }

        if (root.right != null){
            root.depth++;
            maxDepth(root.right);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node = solution.makeTree();
        solution.scanNode(node);
    }
}
