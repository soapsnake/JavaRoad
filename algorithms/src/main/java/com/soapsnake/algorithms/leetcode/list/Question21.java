package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * 两个链表合并为一个链表,要求:合并后的链表必须是排序的
 * <p>
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
class Question21 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);

        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(5);

        Question21 question21 = new Question21();
        ListNode.printListNode(question21.mergeTwoLists(node1, node2));
    }

    public ListNode mergeTwoLists2(ListNode l1head, ListNode l2head) {
        if (l1head == null) {
            return l2head;
        }
        if (l2head == null) {
            return l1head;
        }
        if (l1head.val <= l2head.val) {
            //l1比较小,所以l1是新链表的表头,那么只需要继续递归计算l1的下一个节点即可,l1的下一个节点只可能在l1的next和
            //l2的head当中产生
            l1head.next = mergeTwoLists2(l1head.next, l2head);
            return l1head;
        } else {
            l2head.next = mergeTwoLists2(l1head, l2head.next);
            return l2head;
        }
    }


    //递归版实现,我是想不出来这么好的解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //鲁棒性
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            //假如l1链表头的值比l2链表头的值小,那么l1的链表头就是新链表的表头
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fakehead = new ListNode(0);
        fakehead.next = head;
        ListNode pre = fakehead;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fakehead.next;
    }

}
