package com.soapsnake.algorithms.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-01 20:33
 */
public class Question138 {

    public static void main(String[] args) {
        Question138 question138 = new Question138();
        Node tail = new Node(2, null, null);
        tail.random = tail;
        Node head = new Node(1, tail, tail);
        System.out.println("");
        System.out.println(question138.copyRandomList(head));

    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> cache = new HashMap<>();
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            cache.put(cur, new Node(cur.val, null, null));
            cur = cur.next;
        }

        Node newNode = head;
        while (newNode != null) {
            cache.get(newNode).next = cache.get(newNode.next);     //这里的两部其实是在建立各个节点之间的联系
            cache.get(newNode).random = cache.get(newNode.random);
            newNode = newNode.next;
        }
        //这一步其实更加容易错
        return cache.get(head);
    }

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }

//        @Override
//        public String toString() {
//            return "Node{" +
//                    "val=" + val +
//                    ", next=" + next +
//                    ", random=" + random +
//                    '}';
//        }
    }
}
