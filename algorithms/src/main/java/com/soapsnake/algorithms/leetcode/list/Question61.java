package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-27 10:52
 */
public class Question61 {

    public static void main(String[] args) {
        Question61 question61 = new Question61();
        ListNode head = ListNode.makeTestListFor61();            // 0 -> 1 -> 2 -> 3 -> 4
        System.out.println(question61.rotateRight(head, 5));  //3 -> 4 -> 0 -> 1 -> 2
    }

    /**
     * Input: 0->1->2->NULL, k = 4
     * Output: 2->0->1->NULL
     * Explanation:
     * rotate 1 steps to the right: 2->0->1->NULL
     * rotate 2 steps to the right: 1->2->0->NULL
     * rotate 3 steps to the right: 0->1->2->NULL
     * rotate 4 steps to the right: 2->0->1->NULL
     */
    //leetcode61
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

        int real = 0;  //需要截断的节点数量
        if (k >= length) {
            real = length - k % length;
        } else {
            real = length - k;
        }
        ListNode fast = head;
        while (real - 1 > 0) {
            fast = fast.next;
            real--;
        }
        //到这里时,fast.next指针指向的节点就是需要截断然后挂到链表头的节点
        if (fast.next == null) {
            return head;
        }
        ListNode newList = fast.next;
        fast.next = null;  //打断链表
        ListNode temp = newList;
        while (temp.next != null) {
            temp = temp.next;  //指针从新链表表头移动到表尾部
        }
        temp.next = head; //新表尾和原始表头链接
        return newList;
    }
}
