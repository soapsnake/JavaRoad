package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * 
 * Created on 2020-10-07
 */
public class Question701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        helper(root, val);
        return root;
    }

    private void helper(TreeNode root, int val) {
        if (root.val < val && root.right == null) {
            //如果root的值比val的值小,并且root无右孩子,那么该节点就是右孩子
            root.right = new TreeNode(val);
        } else if (root.val > val && root.left == null) {
            //如果root的值比val的值大,并且root无左孩子,那么该节点就是左孩子
            root.left = new TreeNode(val);
        } else if (root.val > val) {
            //如果root的值比val的值大,并且root有左孩子,那么在左孩子中递归
            helper(root.left, val);
        } else {
            //如果root的值比val的值小,并且root有右孩子,那么在右孩子中递归
            helper(root.right, val);
        }
    }
}
