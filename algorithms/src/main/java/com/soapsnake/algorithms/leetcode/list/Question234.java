package com.soapsnake.algorithms.leetcode.list;

class Question234 {

    public static void main(String[] args) {
        Question234 question234 = new Question234();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

        System.out.println(question234.isPalindrome(head));
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
}
