package com.soapsnake.algorithms.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-01 20:33
 */
public class Question138 {

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", random=" + random +
                    '}';
        }
    }

    Map<Node, Node> cache = new HashMap<>();
    public Node copyRandomList(Node head) {



        return null;
    }

    public static void main(String[] args) {
        Question138 question138 = new Question138();
        Node tail = new Node(2, null, null);
        tail.random = tail;
        Node head = new Node(1, tail, tail);

        System.out.println(question138.copyRandomList(head));

    }
}
