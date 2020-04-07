package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 *
 * Created on 2020-03-03
 */
public class Question445 {

    /**
     * Example:
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 8 -> 0 -> 7
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //1. 分别reverse l1 和 l2
        ListNode l1point = this.reverseList(l1);
        ListNode l2point = this.reverseList(l2);

        //2. 分别两个指针指向两个链表,游离add to l1, if l1 longer than l2....
        int step = 0;
        ListNode l3head = new ListNode(0);
        ListNode l3point = l3head;
        while (l1point != null || l2point != null) {
            int l1value = l1point != null ? l1point.val : 0;
            int l2value = l2point != null ? l2point.val : 0;
            int temp = l1value + l2value + step;
            int value = temp % 10;
            step = temp / 10;
            l3point.next = new ListNode(value);
            l1point = l1point != null ? l1point.next : null;
            l2point = l2point != null ? l2point.next : null;
            l3point = l3point.next;
        }
        //3. reverse l1, and return it
        return this.reverseList(l3head.next);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        ListNode newHead = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = temp;
        }
        return newHead;
    }

}
