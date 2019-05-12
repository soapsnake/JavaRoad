package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.datastructures.list.ListNode;

public class Question147 {


    /**
     * 用插入排序实现链表排序
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     *
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     */
    public ListNode insertionSortList(ListNode head) {
        if( head == null ){
            return head;
        }

        //首先需要搞明白插入排序到底是怎么插入的
        ListNode helper = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = helper; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        int i = 0;
        while( cur != null ){
            next = cur.next;

            //find the right place to insert
            while( pre.next != null && pre.next.val < cur.val ){
                pre = pre.next;
                ListNode.printListNode(helper);
            }
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;

            cur = next;
        }

        return helper.next;
    }

    public static void main(String[] args) {
        Question147 question147 = new Question147();

        question147.insertionSortList(ListNode.makeTestListFor147());
    }
}
