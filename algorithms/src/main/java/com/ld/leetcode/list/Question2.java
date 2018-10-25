package com.ld.leetcode.list;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class Question2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        Question2 q = new Question2();
        ListNode.printListNode(l1);
        ListNode.printListNode(l2);

        ListNode.printListNode(q.addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode revertl1 = revert(l1);
        ListNode revertl2 = revert(l2);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while (revertl1 != null) {
            sb1.append(revertl1.val);
            revertl1 = revertl1.next;
        }

        while (revertl2 != null) {
            sb2.append(revertl2.val);
            revertl2 = revertl2.next;
        }

        char[] c1 = sb1.toString().toCharArray();
        char[] c2 = sb2.toString().toCharArray();
        char[] c3 = new char[Math.max(c1.length, c2.length)];
        int temp = 0;
        char[] big = c1.length > c2.length ? c1 : c2;
        for (int i = 0; i < Math.max(c1.length, c2.length); i++) {
            if (i < Math.min(c1.length, c2.length)) {
                int i1 = Integer.parseInt(String.valueOf(c1[i]));
                int i2 = Integer.parseInt(String.valueOf(c2[i]));
                int dest = 0;
                if (i1 + i2 >= 10) {
                    temp = 1;
                    dest = i1 + i2 - 10;
                    c3[i] = (char) (dest + '0');
                    continue;
                }
                dest = i1 + i2 + temp;
                c3[i] = (char) (dest + '0');
                temp = 0;
            } else {
                c3[i] = big[i];
            }
        }

        int i = 1;
        ListNode head = new ListNode(Integer.parseInt(String.valueOf(c3[0])));
        ListNode last = head;
        while (i < c3.length) {
            int c = Integer.parseInt(String.valueOf(c3[i]));
            ListNode t = new ListNode(c);
            last.next = t;
            last = t;
            i++;
        }
        return revert(head);
    }

    private ListNode revert(ListNode node) {
        //反转链表
        ListNode tail = new ListNode(node.val);

        while (node.next != null) {
            ListNode newNode = new ListNode(node.next.val);
            newNode.next = tail;
            node = node.next;
            tail = newNode;
        }
        return tail;
    }

    //解法2
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
    }

}
