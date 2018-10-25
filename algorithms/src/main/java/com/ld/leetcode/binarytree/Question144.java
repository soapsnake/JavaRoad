package com.ld.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历(先根)
 * <p>
 * 3
 * /\
 * 9   20
 * / \
 * 15    7
 * /  \
 * 8     12
 * / \     / \
 * 6   9   10   22
 * /  \
 * 4     14
 */
public class Question144 {

    /**
     * 2
     * / \
     * 1    3
     * \
     * 4
     *
     * @param args
     */
    public static void main(String[] args) {
        Question144 question144 = new Question144();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);

        List<Integer> res = question144.preorderTraversal(root);
        System.out.println(res);

        List<Integer> res2 = question144.preorderTraversal2(root);

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                res.add(cur.val);
                if (cur.right != null) {
                    stack.add(cur.right);
                }
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
            }
        }
        return res;
    }

    //递归版本是有多简单
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "->");
            preorderTraversal2(root.left);
            preorderTraversal2(root.right);
        }
        return null;
    }

}
