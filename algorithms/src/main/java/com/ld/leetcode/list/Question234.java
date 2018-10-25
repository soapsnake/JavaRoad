package com.ld.leetcode.list;

public class Question234 {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(ListNode head) {

        if (head == null) {
            return true;
        }
        ListNode newHead = head;
        while (head != null) {
            ListNode nextNode = head.next;
            newHead = new ListNode(nextNode.val);
            newHead.next = head;
            head = head.next;
        }

        return false;
    }
}
