package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.datastructures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-10 20:32
 */
public class Question203 {

    //链表的题目真心都不简单,及其容易把人搞晕
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = head;
        ListNode prev = fakeHead;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = next;
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        Question203 question203 = new Question203();
        ListNode head = ListNode.makeTestList();
        ListNode.printListNode(question203.removeElements(head, 1));
    }

}
