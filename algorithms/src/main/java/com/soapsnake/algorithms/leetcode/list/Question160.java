package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 */
class Question160 {

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

    /**
     * Visualization of this solution:
     * Case 1 (Have Intersection & Same Len):
     *
     *        a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *        b
     *             a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             b
     *                  a
     * A:     a1 → a2 → a3
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  b
     * A:     a1 → a2 → a3
     *                    ↘ a
     *                      c1 → c2 → c3 → null
     *                    ↗ b
     * B:     b1 → b2 → b3
     * Since a == b is true, end loop while(a != b), return the intersection node a = c1.
     *
     * Case 2 (Have Intersection & Different Len):
     *
     *             a
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *        b
     *                  a
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             b
     * A:          a1 → a2
     *                    ↘ a
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  b
     * A:          a1 → a2
     *                    ↘      a
     *                      c1 → c2 → c3 → null
     *                    ↗ b
     * B:     b1 → b2 → b3
     * A:          a1 → a2
     *                    ↘           a
     *                      c1 → c2 → c3 → null
     *                    ↗      b
     * B:     b1 → b2 → b3
     * A:          a1 → a2
     *                    ↘                a = null, then a = b1
     *                      c1 → c2 → c3 → null
     *                    ↗           b
     * B:     b1 → b2 → b3
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗                b = null, then b = a1
     * B:     b1 → b2 → b3
     *        a
     *             b
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *             a
     *                  b
     * A:          a1 → a2
     *                    ↘
     *                      c1 → c2 → c3 → null
     *                    ↗
     * B:     b1 → b2 → b3
     *                  a
     * A:          a1 → a2
     *                    ↘ b
     *                      c1 → c2 → c3 → null
     *                    ↗ a
     * B:     b1 → b2 → b3
     * Since a == b is true, end loop while(a != b), return the intersection node a = c1.
     *
     * Case 3 (Have No Intersection & Same Len):
     *
     *        a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *        b
     *             a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *             b
     *                  a
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *                  b
     *                       a = null
     * A:     a1 → a2 → a3 → null
     * B:     b1 → b2 → b3 → null
     *                       b = null
     * Since a == b is true (both refer to null), end loop while(a != b), return a = null.
     *
     * Case 4 (Have No Intersection & Different Len):
     *
     *        a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *        b
     *             a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *             b
     *                  a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                  b
     *                       a
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                       b = null, then b = a1
     *        b                   a = null, then a = b1
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *             b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *        a
     *                  b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *             a
     *                       b
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                  a
     *                            b = null
     * A:     a1 → a2 → a3 → a4 → null
     * B:     b1 → b2 → b3 → null
     *                       a = null
     * Since a == b is true (both refer to null), end loop while(a != b), return a = null.
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode curA = headA;
        ListNode curB = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (curA != curB) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }
}
