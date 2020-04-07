package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 *
 * Created on 2020-03-29
 */
public class Question513 {

    //大神递归版
    //leetcode513 找出树最底下一层的最左边的元素
    public int findBottomLeftValue(TreeNode root) {
                                                    //0存结果,1存最大深度
        return findBottomLeftValue(root, 1, new int[] {0, 0});
    }

    public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
        //如果深度下探了一层,那么更新当前值和最大深度
        if (res[1] < depth) {
            res[0] = root.val;
            res[1] = depth;
        } else if (root.left != null) {
            //如果还是原来的深度并且左节点不为空,那么递归深入左子节点
            findBottomLeftValue(root.left, depth + 1, res);
        } else if (root.right != null) {
            //如果还是原来的深度并且左节点为空但是右节点不为空,那么递归深入右子节点
            findBottomLeftValue(root.right, depth + 1, res);
        }
        //到这里已经是左右子节点都为空,是叶子节点了,可以返回了
        return res[0];
    }

    //傻逼bfs版
    public int findBottomLeftValue2(TreeNode root) {
        //思路,bfs广度优先算法
        List<List<TreeNode>> finalRes = new ArrayList<>();
        finalRes.add(Arrays.asList(root)); //第一层
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> rows = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    rows.add(cur.left);
                }
                if (cur.right != null) {
                    rows.add(cur.right);
                }
            }
            if (!rows.isEmpty()) {
                finalRes.add(rows);
                queue.addAll(rows);
            }
        }
        List<TreeNode> finalRow = finalRes.get(finalRes.size() - 1);
        return finalRow.get(0).val;
    }
}
