package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/30 21:08
 */
public class Question530 {

    //和783题一模一样
    public int getMinimumDifference(TreeNode root) {
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
}
