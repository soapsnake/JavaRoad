package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.datastructures.list.ListNode;

class Question234 {

    public static void main(String[] args) {
        Question234 question234 = new Question234();
        System.out.println(question234.isPalindrome2(ListNode.makeTestListFor234()));
    }

    /**
     * Input: 1->2->2->1
     * Output: true
     *
     * 1. 找到中间节点
     * 2. 后半倒转
     * 3. 比原始和后半
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;

        //寻找链表中间节点slow
        while (fast != null && fast.next !=null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode cur = slow;
        ListNode newHead = null;
        //后半截链表翻转
        while (cur != null) {
            ListNode nextnode = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = nextnode;
        }
        ListNode origin = head;

        //比对链表翻转后的后半截与原始链表的前半截
        while (origin != null && newHead != null) {
            if (origin.val != newHead.val) {
                return false;
            }
            origin = origin.next;
            newHead = newHead.next;
        }
        return true;
    }

    //把链表倒转, 然后和原始链表进行比较,时间复杂度 2*O(n)
    //解法不可行,做链表倒转是一定会破坏原始链表的,不能保存原始链表那么最后的比较就无法进行
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode cur = head;
        ListNode newHead = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }

        while (head != null) {
            if (newHead == null) {
                return false;
            }
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }
}
