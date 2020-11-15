package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

public class Question147 {

    public static void main(String[] args) {
        Question147 question147 = new Question147();
        //-1->5->3->4->0
        ListNode res = question147.insertionSortList(ListNode.makeTestListFor147());
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
            }

            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = fakeHead;    //pre指针回归初始位置,也就是每一次pre指针都是从表头遍历到cur指针
            cur = next;
        }

        return fakeHead.next;
    }

//    public static void main(String[] args) {
//        int[] nums = {15,7, 8 ,21,43, 18, 9, 4, 28, 37};
//        insertSort2(nums);
//        System.out.println(Arrays.toString(nums));
//    }

    public int maxPower(String s) {
        int ans = 1;
        for (int i = 1, cnt = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                if (++cnt > ans) {
                    ans = cnt;
                }
            }else {
                cnt = 1;
            }
        }
        return ans;
    }

}
