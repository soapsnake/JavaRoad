package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-12 22:57
 */
public class Question107 {

    public static void main(String[] args) {
        Question107 question107 = new Question107();
        TreeNode node = TreeNode.makeNormalTree();
        System.out.println(question107.levelOrderBottom(node));
    }

    //迭代版
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).add(root);
        res.add(Arrays.asList(root.val));
        while (!queue.isEmpty()) {
            List<TreeNode> row = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    row.add(node.left);
                }
                if (node.right != null) {
                    row.add(node.right);
                }
            }
            List<Integer> rowInts = new ArrayList<>();
            for (TreeNode node : row) {
                rowInts.add(node.val);   //每一行的数字
                ((LinkedList<TreeNode>) queue).add(node);
            }
            if (rowInts.size() != 0) {
                res.add(0, rowInts);
            }
        }
        return res;
    }
}
