package com.ld.leetcode.list;

public class Question206 {

    public ListNode reverseList(ListNode head) {

        if (head == null){
            return null;
        }

        ListNode tail = new ListNode(head.val);

        while (head.next != null){
            ListNode newNode = new ListNode(head.next.val);
            newNode.next = tail;
            tail = newNode;
            head = head.next;
        }
        return tail;
    }

    public ListNode reverseList2(ListNode head) {
        return reverseList2(head, null);
    }

    //这个递归版本的不太对
    public ListNode reverseList2(ListNode head, ListNode newHead) {
        if (head == null){
            return null;
        }

        if (newHead == null){
            newHead = new ListNode(head.val);
        }
        newHead.next = head;
        reverseList2(head.next, newHead);
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.addNode(new ListNode(2));
        listNode.addNode(new ListNode(3));
        ListNode.printListNode(listNode);

        Question206 question206 = new Question206();
        ListNode.printListNode(question206.reverseList4(listNode));
    }

    // Recursion: 递归版实现
    public ListNode reverseList3(ListNode head) {
        return helper(null, head);
    }

    ListNode helper(ListNode reversed, ListNode remaining) {
        if(remaining==null) return reversed;
        ListNode tmp = remaining.next;
        remaining.next = reversed;
        return helper(remaining, tmp);
    }

    // Iteration:  遍历版实现
    public ListNode reverseList4(ListNode head) {
        if(head==null) return head;
        ListNode newhead = new ListNode(0);
        newhead.next = head;

        while(head.next!=null) {
            ListNode tmp = head.next;
            head.next = head.next.next;

            tmp.next = newhead.next;
            newhead.next = tmp;
        }
        return newhead.next;
    }


}
