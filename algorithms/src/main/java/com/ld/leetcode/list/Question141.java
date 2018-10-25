package com.ld.leetcode.list;

public class Question141 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.addNode(new ListNode(2));
        node.addNode(new ListNode(3));
        node.addNode(new ListNode(4));

        ListNode spe = new ListNode(5);

        node.next = spe;
        spe.next = new ListNode(6);
        spe.next.next = new ListNode(7);
        spe.next.next.next = spe;

        Question141 question141 = new Question141();
        System.out.println(question141.hasCycle(node));

    }

    //快慢指针探测list是否有循环,慢速指针一次一步,快速指针一次两步
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow_point = head;
        ListNode fast_point = head;

        while (fast_point.next != null && fast_point.next.next != null) {
            slow_point = head;
            fast_point = fast_point.next.next;
            head = head.next;

            if (fast_point == slow_point) {
                return true;
            }
        }
        return false;
    }

}
