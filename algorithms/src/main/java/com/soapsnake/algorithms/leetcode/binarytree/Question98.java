package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-07 23:27
 */
public class Question98 {

    //验证是否二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {  //如果root的值超过了最大值或者最小值,说明不合法
            return false;
        }

        //root所有左子节点的值,都应该在最小值 ~ root.val之间   所有右子节点的值,都应该在root.val ~ 最大值之间
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
