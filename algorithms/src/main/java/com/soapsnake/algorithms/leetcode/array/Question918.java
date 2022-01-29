package com.soapsnake.algorithms.leetcode.array;

import com.soapsnake.algorithms.structures.list.ListNode;
import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-05-15
 */
public class Question918 {

    //循环子数组的最大子数组和

    /**
     * 思路: 只需考虑两种情况: 1.最大子数组没有截断  2.最大子数组断成了两半截(因为是循环数组,两边可以接起来的)
     * 第一种情况,只需要按照非循环数组的连续最大子数组和计算方式来算
     * 第二种情况,只需要算出数组总和,然后按第一种情况反向计算最小连续子数组的和,然后:总和 - 最小子数组和 = 最大子数组和
     * 两种情况都算出来后,在比较一次谁更大就可以了,非常的tricky
     * @param nums
     * @return
     */
    //leetcode 918
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int finalMax = Integer.MIN_VALUE, curMax = 0;
        int finalMin = Integer.MAX_VALUE, curMin = 0;
        for (int a : nums) {
            //当前最大加a之后的和
            curMax = Math.max(curMax + a, a);
            //总最大和与当前最大
            finalMax = Math.max(finalMax, curMax);
            //当前最小加a后的和
            curMin = Math.min(curMin + a, a);
            //总最小与当前最小
            finalMin = Math.min(finalMin, curMin);
            total += a;
        }

        //如果总最大大于0, 那么最终结果在  总最大 vs (总和-总最小) 之间产生
        //如果总最大小于等于0, 那么最终结果就是总最大
        return finalMax > 0 ? Math.max(finalMax, total - finalMin) : finalMax;
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
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode oddHead = new ListNode(1);
        ListNode oddCur = oddHead;
        ListNode evenHead = new ListNode(1);
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

    List<Integer> list = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        //bst的先序遍历结果是排序的
        this.preorder(root);
        return list.get(k + 1);
    }

    private void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        preorder(root.left);
        list.add(root.val);
        preorder(root.right);
    }



}
