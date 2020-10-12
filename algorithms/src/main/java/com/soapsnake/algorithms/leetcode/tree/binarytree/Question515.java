package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Created on 2020-03-30
 */
public class Question515 {

    //leetcode515 找出二叉树每一层的最大值
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        res.add(root.val);
        while (!que.isEmpty()) {
            List<TreeNode> row = new ArrayList<>();
            int rowMax = Integer.MIN_VALUE;
            while (!que.isEmpty()) {
                TreeNode cur = que.poll();
                if (cur.left != null) {
                    row.add(cur.left);
                    rowMax = Math.max(rowMax, cur.left.val);
                }
                if (cur.right != null) {
                    row.add(cur.right);
                    rowMax = Math.max(rowMax, cur.right.val);
                }
            }
            if (!row.isEmpty()) {
                res.add(rowMax);
                que.addAll(row);
            }
        }
        return res;
    }
}
