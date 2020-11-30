package com.soapsnake.algorithms.leetcode.dp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 *
 * Created on 2020-04-26
 */
public class Question1143 {

    //leetcode1143
    public int longestCommonSubsequence(String text1, String text2) {
        //最长公共子串
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); ++i)
            for (int j = 0; j < text2.length(); ++j)
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[text1.length()][text2.length()];
    }

//    public static void main(String[] args) {
//        double a = 89.1231321;
//        System.out.println(a);
//
//        DecimalFormat format = new DecimalFormat("#0.00");
//        System.out.println(format.format(a));
//
//        double b = new BigDecimal(a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(b);
//    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    private static ListNode helper(ListNode[] lists, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return lists[l];
        }
        if (l == r - 1) {
            return mergeTwoLists(lists[l], lists[r]);
        }
        int mid = l + (r - l) / 2;
        System.out.println("mid = " + mid);
        ListNode lList = helper(lists, l, mid);
        ListNode rList = helper(lists, mid + 1, r);
        return mergeTwoLists(lList, rList);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode fakshead = new ListNode(0);
        ListNode cur = fakshead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return fakshead.next;
    }

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[] {new ListNode(1).next(4).next(5),
        new ListNode(1).next(3).next(4), new ListNode(2).next(6)};
        System.out.println(mergeKLists(listNodes));
    }

}
