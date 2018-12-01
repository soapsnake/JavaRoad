package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/29 20:23
 */
public class Question783 {

    public int minDiffInBST(TreeNode root) {
        //中序遍历二叉搜索树
        if (root == null) {
            return 0;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < res.size() - 1; i++) {
            min = Math.min(res.get(i + 1) - res.get(i), min);
        }
        return min;
    }

    public static void main(String[] args) {
        Question783 question783 = new Question783();
        System.out.println(question783.minDiffInBST2(TreeNode.makeBinerSearchTree()));
    }

    private int min = Integer.MAX_VALUE;
    private int last = -100000000;   //这个last不能取最小值,因为有可能会溢出,造成得到一个负无穷大的数,被判定成最小值
    public int minDiffInBST2(TreeNode root) {
        System.out.println(last);
        //中序遍历二叉搜索树
        if (root == null) {
            return 0;
        }
        dfs(root);
        return this.min;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        min = Math.min(root.val - this.last, min);   //last取Integer.MIN会造成溢出,得到负无穷大数
        System.out.println(min);
        last = root.val;
        if (root.right != null) {
            dfs(root.right);
        }
    }

    public List<TreeNode> inorderDFS(TreeNode root) {
        if (root == null)
            return null;

        List<TreeNode> res = new ArrayList<>();
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur);
            cur = cur.right; //如果这步后cur=null,大循环也不会退出因为!stack.isEmpty()
        }
        return res;
    }

}
