package com.soapsnake.algorithms.leetcode.list;

import java.util.HashMap;
import java.util.Map;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-25 11:39
 */
public class Question19 {

    public static void main(String[] args) {
        Question19 question19 = new Question19();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(question19.removeNthFromEnd(head, 1));
    }

    //借助map法
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode cur = head;
        int i = 1;
        while (cur != null) {
            map.put(i++, head);
            cur = cur.next;
        }
        ListNode tar = map.get(map.size() - n + 1);   //5
        ListNode prev = map.get(map.size() - n);  //4
        ListNode next = map.get(map.size() - n + 2); //6
        prev.next = next;
        return head;
    }

    //快慢指针,快指针比慢指针快n个节点,所以当快指针到达链表尾部时,慢指针刚好指向要删除的节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode fast = head;
        int i = n;
        while (i > 0) {    //这题最难的就是计算快指针的初始位置
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            i--;
        }

        if (fast == null) {
            //到这里说明刚好需要删除链表头
            head = head.next;
            return head;
        }

        ListNode fakehead = new ListNode(0);  //所有需要删节点的题目都需要用到fakehead
        fakehead.next = cur;
        ListNode prev = fakehead;
        while (fast != null) {
            fast = fast.next;
            cur = cur.next;
            prev = prev.next;

        }
        prev.next = cur.next;
        return fakehead.next;
    }

}
