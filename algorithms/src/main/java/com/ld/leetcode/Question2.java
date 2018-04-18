package com.ld.leetcode;

/**
 *You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class Question2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode revertl1 = revert(l1);
        ListNode revertl2 = revert(l2);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while (revertl1 != null){
            sb1.append(revertl1.val);
            revertl1 = revertl1.next;
        }

        while (revertl2 != null){
            sb2.append(revertl2.val);
            revertl2 = revertl2.next;
        }

        Integer list1 = Integer.parseInt(sb1.toString());
        Integer list2 = Integer.parseInt(sb2.toString());

        Integer dest = list1 + list2;

        char[] chars = String.valueOf(dest).toCharArray();
        int i = 1;
        ListNode x = new ListNode(Integer.parseInt(String.valueOf(chars[0])));
        ListNode last = x;
        while (i < chars.length){
            int c = Integer.parseInt(String.valueOf(chars[i]));
            ListNode t = new ListNode(c);
            last.next = t;
            last = t;
            i++;
        }
        return revert(x);
    }

    private ListNode findTail(ListNode node) {
        if (node == null){
            return null;
        }
        if (node.next != null){
            node = findTail(node.next);
        }
        return node;
    }


    private ListNode revert(ListNode node) {
        //反转链表
       ListNode tail = new ListNode(node.val);

        while (node.next != null){
            ListNode newNode = new ListNode(node.next.val);
            newNode.next = tail;
            node = node.next;
            tail = newNode;
        }
        return tail;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        Question2 q = new Question2();
        ListNode.printListNode(l1);
        ListNode.printListNode(l2);

        ListNode.printListNode(q.addTwoNumbers(l1 , l2));
    }

}
