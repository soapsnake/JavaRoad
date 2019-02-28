package com.soapsnake.algorithms.leetcode.list;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-27 10:52
 */
public class Question61 {

    /**
     * Input: 0->1->2->NULL, k = 4
     * Output: 2->0->1->NULL
     * Explanation:
     * rotate 1 steps to the right: 2->0->1->NULL
     * rotate 2 steps to the right: 1->2->0->NULL
     * rotate 3 steps to the right: 0->1->2->NULL
     * rotate 4 steps to the right: 2->0->1->NULL
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return null;
        }

        int length = 0;
        ListNode cur = head;
        //不要求只遍历一次的话可以先遍历一次求一下链表长度,然后后面的就好办了
        while (cur != null) {
            cur = cur.next;
            length++;
        }

        int real = 0;
        if (k >= length) {
            real = length - k % length;
        } else {
            real = length - k;
        }
        ListNode fast = head;
        while (real - 1  > 0) {
            fast = fast.next;
            real--;
        }
        if (fast.next == null) {
            return head;
        }
        ListNode newList = fast.next;
        fast.next = null;
        ListNode temp = newList;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        return newList;
    }

    public static void main(String[] args) {
        Question61 question61 = new Question61();
        ListNode head = ListNode.makeTestListFor61();            // 0 -> 1 -> 2 -> 3 -> 4
        System.out.println(question61.rotateRight(head, 5));  //3 -> 4 -> 0 -> 1 -> 2
    }
}
