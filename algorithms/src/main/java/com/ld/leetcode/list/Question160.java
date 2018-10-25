package com.ld.leetcode.list;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 */
public class Question160 {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        headA.addNode(new ListNode(2));
        headA.addNode(new ListNode(3));
        headA.addNode(new ListNode(4));
        ListNode common = new ListNode(6);
        headA.addNode(common);
        headA.addNode(new ListNode(5));

        ListNode headB = new ListNode(7);
        headB.addNode(new ListNode(8));
        headB.addNode(common);
        headB.addNode(new ListNode(5));

        Question160 question160 = new Question160();
        ListNode.printListNode(question160.getIntersectionNode(headA, headB));

//        ListNode.printListNode(question160.revertList(headA));

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null) {
            return null;
        }
        if (headB == null) {
            return null;
        }

        ListNode reverHeadA = revertList(headA);
        ListNode reverHeadB = revertList(headB);

        if (reverHeadA.val != reverHeadB.val) {
            return null;
        }

        ListNode last = reverHeadA;
        last.next = reverHeadA;
        while (reverHeadA.next != null && reverHeadB.next != null) {
            if (reverHeadA.val != reverHeadB.val) {
                return new ListNode(last.val);
            }
            last = reverHeadA;
            reverHeadA = reverHeadA.next;
            reverHeadB = reverHeadB.next;
        }
        return reverHeadA;
    }

    public ListNode revertList(ListNode root) {
        if (root == null) {
            return null;
        }

        ListNode head;
        ListNode newlist = null;
        while (root != null) {
            head = new ListNode(root.val);
            head.next = newlist;
            newlist = head;
            root = root.next;
        }
        return newlist;
    }

    //简明解法
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
