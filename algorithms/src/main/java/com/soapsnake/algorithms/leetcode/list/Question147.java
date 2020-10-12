package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

public class Question147 {


    public static void main(String[] args) {
        Question147 question147 = new Question147();
        //-1->5->3->4->0
        ListNode res = question147.insertSortList(ListNode.makeTestListFor147());
        ListNode.printListNode(res);
    }

    public static void insertSort2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    System.arraycopy(nums, j, nums, j + 1, i - j);
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 用插入排序实现链表排序
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * <p>
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }

        //首先需要搞明白插入排序到底是怎么插入的
        ListNode fakeHead = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = fakeHead; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        while (cur != null) {
            next = cur.next;
            //find the right place to insert
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
//                ListNode.printListNode(helper);
            }
            //到这里有两种情况: 1. pre.next == null 2.pre.next > cur 也就是找到了一个比cur大的元素,需要把cur插到这个位置

            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = fakeHead;    //pre指针回归初始位置,也就是每一次pre指针都是从表头遍历到cur指针

            cur = next;   //晚上回去搞这一题
        }

        return fakeHead.next;
    }

//    public static void main(String[] args) {
//        int[] nums = {15,7, 8 ,21,43, 18, 9, 4, 28, 37};
//        insertSort2(nums);
//        System.out.println(Arrays.toString(nums));
//    }

    public ListNode insertSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode();
        ListNode pre = fakeHead;
//		pre.next = head;    //这个一旦打开就会死循环
        ListNode cur = head;        //相当于i指针
        while (cur != null) {
            ListNode next = cur.next;
            //cur指针已经就位,从表头开始遍历
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;    //这一步赋值,会永久改变pre的值,也会导致fakeHead的值跟着改变
            pre = fakeHead;   //pre需要返回到链表头部的位置
            cur = next;
        }
        return fakeHead.next;
    }
}
