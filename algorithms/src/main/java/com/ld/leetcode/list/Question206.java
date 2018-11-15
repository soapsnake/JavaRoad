package com.ld.leetcode.list;

public class Question206 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.addNode(new ListNode(2));
        listNode.addNode(new ListNode(3));
        listNode.addNode(new ListNode(4));
        listNode.addNode(new ListNode(5));
        ListNode.printListNode(listNode);

        Question206 question206 = new Question206();
        ListNode.printListNode(question206.reverseList(listNode));
    }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;

        while (head != null) {
            ListNode next = head.next;

            head.next = newHead;

            newHead = head;

            head = next;
        }

        return newHead;
    }

}
