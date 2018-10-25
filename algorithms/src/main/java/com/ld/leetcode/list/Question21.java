package com.ld.leetcode.list;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class Question21 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);

        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(5);

        Question21 question21 = new Question21();
        ListNode.printListNode(question21.mergeTwoLists(node1, node2));

    }

    //递归版实现,我是想不出来这么好的解法
    public ListNode mergeTwoLists(ListNode l1head, ListNode l2head) {
        //鲁棒性
        if (l1head == null) return l2head;
        if (l2head == null) return l1head;
        if (l1head.val < l2head.val) {
            //假如l1链表头的值比l2链表头的值小,那么l1的链表头就是新链表的表头
            l1head.next = mergeTwoLists(l1head.next, l2head);
            return l1head;
        } else {
            l2head.next = mergeTwoLists(l1head, l2head.next);
            return l2head;
        }
    }
}
