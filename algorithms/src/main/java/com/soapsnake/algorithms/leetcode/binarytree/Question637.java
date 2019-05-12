package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;
import com.soapsnake.algorithms.leetcode.array.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author soapsnake
 * @date 2018/11/9
 */
class Question637 {

    //计算二叉树每层节点的平均值,利用队列实现,复杂度O(down²)
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        ((LinkedList<TreeNode>) queue).addLast(root.left);   //尾部入队
        ((LinkedList<TreeNode>) queue).addLast(root.right);   //尾部入队
        res.add((double) root.val);

        while (!queue.isEmpty()) {   //外部大循环
            List<TreeNode> rowNodes = new ArrayList<>();
            while (!queue.isEmpty()) {     //这里是循环一行
                TreeNode node = queue.poll();   //头部出队
                if (node != null) {
                    rowNodes.add(node);   //一行添加完s
                }
            }
            if (rowNodes.size() == 0) {
                break;
            }

            double total = 0;
            for (TreeNode node : rowNodes) {
                 total += node.val;
                 ((LinkedList<TreeNode>) queue).addLast(node.left);
                 ((LinkedList<TreeNode>) queue).addLast(node.right);
            }
            res.add(total / (double) rowNodes.size());
        }
        return res;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        ((LinkedList<Integer>) queue).add(null);

       Integer res =  queue.poll();

//        System.out.println(res);

        List<Integer> arr = new ArrayList<>();
        arr.add(null);
        arr.add(null);

        System.out.println(arr.size());


        Map<Object, Object> map = new HashMap<>();
        map.put(null, "1");
        map.put("1", null);
        map.put(null, "2");

        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " ---->" + entry.getValue());
        }


        Question637 question637 = new Question637();
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);

        node.left.left = new TreeNode(15);
        node.left.right = new TreeNode(7);

        List<Double> list = question637.averageOfLevels(TreeNode.makeNormalTree());
        ArrayUtils.printList(list);
    }

}
