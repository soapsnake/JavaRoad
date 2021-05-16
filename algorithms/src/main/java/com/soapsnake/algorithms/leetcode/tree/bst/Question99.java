package com.soapsnake.algorithms.leetcode.tree.bst;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * 
 * Created on 2020-10-31
 */
public class Question99 {

    /**
     * 基本思想,利用先序遍历,找到需要交换值得两个node指针(大小不对),然后利用指针交换node的值
     * @param root
     */
    //leetcode99
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        first = null;
        second = null;
        pre = null;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private TreeNode first;
    private TreeNode second;
    private TreeNode pre;

    //前序遍历标准写法
    private void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);

        if(first==null && (pre==null ||pre.val>=root.val)){
            first = pre;
        }
        if(first!=null && pre.val>=root.val){
            second = root;
        }
        pre = root;
        inorder(root.right);
    }
}
