package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.datastructures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-16 01:36
 */
public class Question83 {

    /**
     * n平方级别算法,每一个节点,都需要扫描其后的所有节点,如果不平方,可以使用外部存储来校验是否重复
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode pre = cur;
            while (next != null) {
                if (next.val == cur.val) {
                    pre.next = next.next;
                } else {
                    pre = pre.next;
                }
                next = next.next;
            }
            cur =  cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Question83 question83 = new Question83();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(question83.deleteDuplicates(head));
    }
}
