package com.soapsnake.algorithms.leetcode.list;


import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Given linked list -- head = [4,5,1,9], which looks like following:
 */
class Question237 {

    public static void main(String[] args) {
        Question237 question237 = new Question237();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode.printListNode(head);

        ListNode.printListNode(head);
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
