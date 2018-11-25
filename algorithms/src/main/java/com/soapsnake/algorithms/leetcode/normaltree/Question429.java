package com.soapsnake.algorithms.leetcode.normaltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author soapsnake
 * @date 2018/11/11
 */
class Question429 {

    public List<List<Integer>> levelOrder(Node root) {

        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> nodes = new LinkedList<>();
        res.add(Arrays.asList(root.val));
        for (Node node : root.children) {
            if (node != null) {
                nodes.add(node);
            }
        }
        while (!nodes.isEmpty()) {
            List<Node> temp = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                temp.add(node);
                integers.add(node.val);
            }
            res.add(integers);

            for (Node node : temp) {
                for (Node node1 : node.children) {
                    if (node1 != null) {
                        nodes.add(node1);
                    }
                }
            }
        }
        return res;
    }

}
