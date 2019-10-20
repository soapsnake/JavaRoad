package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

public class Question236 {

    /**
     *          3
     *         / \
     *        5   1
     *       /\  /\
     *      6 2 0  8
     *       /\
     *     7   4
     */
    public static void main(String[] args) {
        Question236 question236 = new Question236();
//        TreeNode.layerTravse(TreeNode.makeNormalTreeFor236());
        TreeNode root = TreeNode.makeNormalTreeFor236();
        TreeNode p = root.left.right.left;
        TreeNode q = root.left.right.right;
        TreeNode answer = question236.findLCA(root, p , q);
        System.out.println(answer);
        System.out.println(answer.val);
    }

    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor4(root,p,q);
        return LCA;
    }
    TreeNode LCA = null;
    public boolean lowestCommonAncestor4(TreeNode currentNode, TreeNode p, TreeNode q) {
            if (currentNode == null) {
                return false;
            }
            int left = lowestCommonAncestor4(currentNode.left,p,q) ? 1 : 0;
            int rifht = lowestCommonAncestor4(currentNode.right,p, q) ? 1: 0;
            int mid = currentNode == p || currentNode == q ? 1 : 0;
            if (left + rifht + mid >= 2) {
                LCA = currentNode;
            }
            return (left + rifht + mid >= 1);
    }

        //https://www.youtube.com/watch?v=py3R23aAPCA  需要看一下原理
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root == p || root == q){
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.left,p,q);

        TreeNode r = lowestCommonAncestor(root.right,p,q);

        if(l != null && r != null){
            return root;
        }
        return l != null ? l:r;
    }

    //最低公共祖先,傻逼版解法
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (canReach(root, p) && canReach(root, q)) {
            target = root;
            lowestCommonAncestor2(root.left, p, q);
            lowestCommonAncestor2(root.right, p, q);
        }
        return target;
    }
    TreeNode target = null;

    private boolean canReach(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root.val == p.val) {
            return true;
        }
        return canReach(root.left, p) || canReach(root.right, p);
    }

    private TreeNode ans;
    public Question236() {
        // Variable to store LCA node.
        this.ans = null;
    }

    //leetcode官方解法
    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // If reached the end of a branch, return false.   //如果达到了叶子节点,那就回溯
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        //如果在左子树发现了p或者q,那么left为true(这里赋值为1)
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        //如果在右子树发现了p或者q,那么right为true(这里赋值为1)
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        //如果当前节点就是p或者q,那么mid为true   //这个没弄明白
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;

        // If any two of the flags left, right or mid become True
        //如果mid,left,right 3中有2满足条件,那么该节点就是lca节点
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        //如果三种有任1满足条件,那么久可能是以下三种情况之一: cur为p或者q, cur左子树有目标, cur右子树有目标
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
}
