package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

class Question206 {

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
        // 模板:链表翻转
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }

        return newHead;
    }

}
