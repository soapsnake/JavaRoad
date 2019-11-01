package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-25 00:12
 */
public class Question133 {


    Map<Integer, Node> cache = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (cache.get(node.val) != null) {
            //为什么可以这样搞:因为这个图里绝对不会出现val相同的两个节点
            //如果这个图里可以出现两个val相同的节点,就不能用这个方式了
            return cache.get(node.val);
        }
        Node newNode = new Node(node.val, null);
        newNode.neighbors = new ArrayList<>();
        cache.put(newNode.val, newNode);
        for (Node node1 : node.neighbors) {
            newNode.neighbors.add(cloneGraph(node1));
        }
        return newNode;
    }

}
