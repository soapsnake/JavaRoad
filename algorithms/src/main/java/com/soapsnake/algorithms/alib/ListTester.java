package com.soapsnake.algorithms.alib;

import java.util.List;

import com.soapsnake.algorithms.structures.list.ListNode;
import org.junit.Test;

public class ListTester {


    public static ListNode revers(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }


    public static void main(String[] args) {
        ListNode.printListNode(deleteDup(ListNode.makeTestListFor82()));
    }

    public static ListNode reverseTwo(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = fakeHead;
        //fake(cur) -> first -> second -> second.next -> ...

        while (cur != null && cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            first.next = second.next;
            second.next = first;
            cur.next = second;
            cur = cur.next.next;
        }
        return fakeHead.next;
    }

    public static ListNode deleteNode(ListNode head, int dest) {
        if (null == head) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = head;
        ListNode pre = fakeHead;
        //pre -> cur -> next ->
        //pre -> next

        //用cur指针做删除必然会遇到的一些坑,连续目标节点删不干净
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == dest) { //有删除,
                pre.next = next;
            } else {  //没有删除,cur指针和pre指针正常移动
                pre = pre.next;
            }
            cur = next;
        }
        return fakeHead.next;
    }

    public static ListNode deleteDup(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode cur = head;
        ListNode pre = fakeHead;
        //pre -> cur -> next ->
        //pre -> next

        //用cur指针做删除必然会遇到的一些坑,连续目标节点删不干净
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == next.val) { //有删除,
                pre.next = next;
            } else {  //没有删除,cur指针和pre指针正常移动
                pre = pre.next;
            }
            cur = next;
        }
        return fakeHead.next;
    }


    //合并两个排序的链表
    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode fakehead = new ListNode(0);
        ListNode cur = fakehead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return fakehead.next;
    }

    public ListNode mergeList2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeList2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeList2(list2.next, list1);
            return list2;
        }
    }

    @Test
    public void testMergeList() {
        ListNode list1 = ListNode.makeTestListFor61();
        ListNode list2 = ListNode.makeTestListFor82();
        ListNode newList = mergeList2(list1, list2);
        ListNode.printListNode(newList);
    }

    //判断两个链表是否相交
    public static boolean isFuckedList(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        while (cur1 != null) {

            ListNode cur2 = list2;
            while (cur2 != null) {
                if (cur1.next == cur2) {
                    return true;
                }
                cur2 = cur2.next;
            }
            cur1 = cur1.next;
        }
        return false;
    }

    //单数递增,双数递减,要求对链表进行排序
    public static ListNode sortOddEven(ListNode head) {
        return null;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode pre = fakeHead;
        pre.next = head;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                pre.next = next;
            } else {
                pre = pre.next;
            }
            cur = next;
        }
        return fakeHead.next;
    }

}
