package com.soapsnake.algorithms.leetcode.graph;

import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-25 00:12
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
