package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-08 13:50
 */
public class Question103 {

    public static void main(String[] args) {
        Question103 question103 = new Question103();
        question103.zigzagLevelOrder(TreeNode.makeNormalTreeFor110());
    }

    /**
     * 之字形层状打印二叉树
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its zigzag level order traversal as:
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    //leetcode103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //最简单的方法就是每下一层做一次翻转,从队列的另外一个方向往外拉数据
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean order = true;
        int size = 1;

        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode n = q.poll();
                if (order) {
                    tmp.add(n.val);   //如果从左往右,那就加到末尾
                } else {
                    tmp.add(0, n.val);   //如果从右往左,那就加到开头
                }
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.add(tmp);
            size = q.size();
            order = !order;  //order翻转
        }
        return res;
    }
}
