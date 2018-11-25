package com.soapsnake.algorithms.leetcode.normaltree;

import java.util.List;

class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }


    @Override
    public String toString() {
        return this.val + " -> " + children;
    }


}
