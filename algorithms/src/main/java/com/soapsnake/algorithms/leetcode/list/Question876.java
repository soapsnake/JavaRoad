package com.soapsnake.algorithms.leetcode.list;

class Question876 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);

        ListNode.printListNode(head);

        Question876 question876 = new Question876();

        ListNode.printListNode(question876.middleNode(head));

    }

    public ListNode middleNode(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        //快速指针一次两步,慢速指针一次一步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
