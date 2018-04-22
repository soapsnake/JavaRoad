package com.ld.leetcode;

public class Question160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null){
            return null;
        }
        ListNode tailA = headA;
        ListNode tailAFather = headA;

        ListNode tailB = headB;
        ListNode tailBfather = headB;
        while (headA.next != null){
            tailA = headA.next;
//            tailAFather.next = tailA;
            headA = headA.next;
        }
        while (headB.next != null){
            tailB = headB.next;
            tailBfather.next = tailB;
            headB = headB.next;
        }

        if (tailA != tailB){
            return null;
        }
        while (true){
            if (tailAFather != tailBfather){
                return tailA;
            }
            tailAFather = tailA;
            tailBfather = tailB;
        }
    }

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
        question160.getIntersectionNode(headA, headB);


    }
}
