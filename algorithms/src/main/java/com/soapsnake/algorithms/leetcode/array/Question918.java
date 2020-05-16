package com.soapsnake.algorithms.leetcode.array;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-15
 */
public class Question918 {

    //循环子数组的最大子数组和

    /**
     * 思路: 只需考虑两种情况: 1.最大子数组没有截断  2.最大子数组断成了两半截(因为是循环数组,两边可以接起来的)
     * 第一种情况,只需要按照非循环数组的连续最大子数组和计算方式来算
     * 第二种情况,只需要算出数组总和,然后按第一种情况反向计算最小连续子数组的和,然后:总和 - 最小子数组和 = 最大子数组和
     * 两种情况都算出来后,在比较一次谁更大就可以了,非常的tricky
     * @param A
     * @return
     */
    //leetcode 918
    public int maxSubarraySumCircular(int[] A) {
        int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
        for (int a : A) {
            //当前最大加a之后的和
            curMax = Math.max(curMax + a, a);
            //总最大和与当前最大
            maxSum = Math.max(maxSum, curMax);
            //当前最小加a后的和
            curMin = Math.min(curMin + a, a);
            //总最小与当前最小
            minSum = Math.min(minSum, curMin);
            total += a;
        }

        //如果总最大大于0, 那么最终结果在  总最大 vs (总和-总最小) 之间产生
        //如果总最大小于等于0, 那么最终结果就是总最大
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    /**
     * 奇数节点合并, 偶数节点合并,非节点值得奇偶
     * Example 1:
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     *
     * Example 2:
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode oddHead = new ListNode();
        ListNode oddCur = oddHead;
        ListNode evenHead = new ListNode();
        ListNode evenCur = evenHead;
        ListNode cur = head;
        int i = 1;
        while (cur != null) {
            if (i % 2 != 0) {
                //奇数节点
                oddCur.next = cur;
                oddCur = oddCur.next;
            } else {
                //偶数节点
                evenCur.next = cur;
                evenCur = evenCur.next;
            }
            cur = cur.next;
            i++;
        }
        oddCur.next = evenHead.next;
        return oddHead;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1).next(2).next(3).next(4).next(5);
        System.out.println(node);
    }
}
