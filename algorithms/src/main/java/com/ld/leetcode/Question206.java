package com.ld.leetcode;

public class Question206 {

    public ListNode reverseList(ListNode head) {

        if (head == null){
            return null;
        }

        ListNode tail = new ListNode(head.val);

        while (head.next != null){
            ListNode newNode = new ListNode(head.next.val);
            newNode.next = tail;
            tail = newNode;
            head = head.next;
        }
        return tail;
    }

    public ListNode reverseList2(ListNode head) {
        return reverseList2(head, null);
    }

    //这个递归版本的不太对
    public ListNode reverseList2(ListNode head, ListNode newHead) {
        if (head == null){
            return null;
        }

        if (newHead == null){
            newHead = new ListNode(head.val);
        }
        newHead.next = head;
        reverseList2(head.next, newHead);
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.addNode(new ListNode(2));
        listNode.addNode(new ListNode(3));
        ListNode.printListNode(listNode);

        Question206 question206 = new Question206();
        ListNode.printListNode(question206.reverseList2(listNode));
    }
}
