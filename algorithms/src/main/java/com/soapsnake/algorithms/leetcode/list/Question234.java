package com.soapsnake.algorithms.leetcode.list;

class Question234 {

    public static void main(String[] args) {
        Question234 question234 = new Question234();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

        System.out.println(question234.isPalindrome2(head));
    }

    /**
     * todo
     * 1. 找到中间节点
     * 2. 后半倒转
     * 3. 比原始和后半
     */
    public boolean isPalindrome(ListNode head) {


        return false;
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
