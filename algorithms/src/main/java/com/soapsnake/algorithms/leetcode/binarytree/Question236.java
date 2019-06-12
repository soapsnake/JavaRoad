package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

public class Question236 {


    //https://www.youtube.com/watch?v=py3R23aAPCA  需要看一下原理
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;
    }

    //最低公共祖先,傻逼版解法
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (canReach(root, p) && canReach(root, q)) {
            target = root;
            lowestCommonAncestor(root.left, p, q);
            lowestCommonAncestor(root.right, p, q);
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

    public static void main(String[] args) {
        Question236 question236 = new Question236();
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        System.out.println(question236.lowestCommonAncestor(TreeNode.makeNormalTreeFor236(), p , q));
    }
}
