package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Question199 {

    //二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        res.add(root.val);
        while (!queue.isEmpty()) {
            List<TreeNode> rows = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (null != cur.left)
                    rows.add(cur.left);
                if (null != cur.right)
                    rows.add(cur.right);
            }
            for (int i = 0; i < rows.size(); i++) {
                queue.add(rows.get(i));
                if (i == rows.size() - 1) {
                    res.add(rows.get(i).val);
                }
            }
        }
        return res;
    }
}
