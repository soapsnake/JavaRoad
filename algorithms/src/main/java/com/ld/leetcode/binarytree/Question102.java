package com.ld.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层状遍历
 */
public class Question102 {

    /**
     * 3
     * /\
     * 9   20
     * / \
     * 15    7
     * /  \
     * 8     12
     * / \     / \
     * 6    9  10   22
     * /  \
     * 4     14
     */
    public static void main(String[] args) {
        Question102 question102 = new Question102();
        System.out.println(question102.levelOrder(TreeNode.makeTree()));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        List<List<TreeNode>> result = new ArrayList<>();
        //这个队列完全用来保存中间变量,不做其他用途
        List<TreeNode> queue = new ArrayList<>();
        TreeNode cur = root;
        queue.add(cur);

        int curPoint = 0;
        int endPoint = 1;
        while (curPoint < queue.size()) {
            //每一层都是一个list
            List<TreeNode> layerNodes = new ArrayList<>();
            List<Integer> layerNodesVal = new ArrayList<>();
            endPoint = queue.size();
            while (curPoint < endPoint) {
                cur = queue.get(curPoint);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                //一层
                layerNodes.add(cur);
                layerNodesVal.add(cur.val);
                curPoint++;
            }
            result.add(layerNodes);
            res.add(layerNodesVal);
        }
        return res;
    }
}
