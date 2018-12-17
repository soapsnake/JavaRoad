package com.soapsnake.algorithms.leetcode.list;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-16 01:36
 */
public class Question83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            while (next != null && cur.val == next.val) {
                next = next.next;
            }
            cur.next = next;
            if (next == null) {
                break;
            }
            cur = cur.next;
        }
        System.out.println("cur = " + cur);
        System.out.println("head = " + head);
        return head;
    }

    public static void main(String[] args) {
        Question83 question83 = new Question83();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        System.out.println(question83.deleteDuplicates(head));
    }
}
