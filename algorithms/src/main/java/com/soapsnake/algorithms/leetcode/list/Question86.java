package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

public class Question86 {

    /**
     * Given a linked list and a value x,
     * partition it such that all nodes less than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * Example:
     * Input: head = 1->4->3->2->5->2, x = 3
     * Output: 1->2->2->4->3->5
     */
    //leetcode 86
    public ListNode partition(ListNode head, int x) {
        //思路1:由于题目要求保留所有原始顺序,可以两个list,list1存放比x小的node,
        // list2存放剩下的,然后把list2拼在list1后面
        if (head == null) {
            return head;
        }
        ListNode list1 = new ListNode(0);
        ListNode head1 = list1;
        ListNode list2 = new ListNode(0);
        ListNode head2 = list2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                head1.next = cur;
                head1 = head1.next;
            } else {
                head2.next = cur;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        head2.next = null; //少这步会造成死循环链表
        head1.next = list2.next;
        return list1.next;
    }

    public static void main(String[] args) {
        Question86 question86 = new Question86();

        //1,4,3,2,5,2 -> 1,2,2,4,3,5
        System.out.println(question86.partition(ListNode.makeTestListFor86(), 3));

    }
}
