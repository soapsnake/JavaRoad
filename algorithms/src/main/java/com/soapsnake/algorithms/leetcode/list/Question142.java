package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 09:51
 */
public class Question142 {

    //todo 此题目还没有解
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null) {
            if (slow == fast){
            }
            slow = head.next;
            fast = head.next.next;
        }
        return head;
    }
}
