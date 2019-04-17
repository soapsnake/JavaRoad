package com.soapsnake.algorithms.leetcode.normaltree;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-18 00:13
 */
public class Question116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;  //next指针指向同一级横向右侧的节点
        }
    };

    public Node connect(Node root) {



        return null;
    }
}
