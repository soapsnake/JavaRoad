package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 */
class Question148 {
    public static void main(String[] args) {
        Question148 question148 = new Question148();
        ListNode.printListNode(question148.sortList(ListNode.makeTestListFor147()));

    }

    /**
     * nlog(n) 排序链表,不允许使用额外内存
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;
        //归并排序
        // step 1. cut the list to two halves
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //到这里,head指向链表头,slow指向链表中间
        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head); //排序链表前半段,这个递归会把l1无限切分成更小的链表段
        ListNode l2 = sortList(slow); //排序链表后半段,同上

        // step 3. merge l1 and l2
        return merge(l1, l2);  //把无限切分的两小段合并成一个大链表
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;   //p指针会沿链表头向表尾移动,但是l指针一直指向表头

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;   //如果左链表当前节点值比右链表当前节点值小,那么p链表下一节点指向左链表的节点
                l1 = l1.next;
            } else {
                p.next = l2;   //否则指向右链表的节点
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return fakeHead.next;
    }

}
