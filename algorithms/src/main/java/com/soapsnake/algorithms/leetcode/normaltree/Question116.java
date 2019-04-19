package com.soapsnake.algorithms.leetcode.normaltree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //117题也可以这么解, 不过117要求不能用额外的存储
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        ((LinkedList<Node>) queue).add(root);
        while (!queue.isEmpty()) {
            Stack<Node> row = new Stack<>();
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node != null) {
                    row.push(node);
                }
            }
            for (Node node : row) {   //用for循环遍历栈的时候是先进先出的
                queue.add(node.left);
                queue.add(node.right);
            }
            Node pre = null;
            while (!row.empty()) {
                Node node = row.pop();  //pop弹栈遵循先进后出
                node.next = pre;
                pre = node;
            }
        }
        return root;
    }
}
