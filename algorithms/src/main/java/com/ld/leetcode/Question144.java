package com.ld.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的前序遍历(先根)
 *
 *         3
 *        /\
 *      9   20
 *         / \
 *      15    7
 *          /  \
 *        8     12
 *      / \     / \
 *    6   9   10   22
 *           /  \
 *         4     14
 */
public class Question144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        while(cur !=null || !queue.isEmpty()) {
            while (cur != null) {
                queue.add(cur);
                cur = cur.left;
            }
            cur = queue.remove();
            res.add(cur.val);
            res.add(cur.right.val);
            cur = cur.right;
        }
        return res;
    }

    /**
     *        2
     *       / \
     *     1    3
     *      \
     *       4
     * @param args
     */
    public static void main(String[] args) {
        Question144 question144 = new Question144();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right  = new TreeNode(3);

        List<Integer> res = question144.preorderTraversal(root);
        System.out.println(res);
    }

}
