package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-22 14:24
 */
public class Question725 {

    public static void main(String[] args) {
        Question725 question725 = new Question725();
        ListNode root = ListNode.makeTestListFor725();
        int k = 3;
        System.out.println(Arrays.toString(question725.splitListToParts(root, k)));
    }

    /**
     * Input:
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     * Explanation:
     * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        //todo 这个题目有点难了
        List<ListNode> res = new ArrayList<>();
        //难点在每段链表长度的确定上
        ListNode head = root;
        Integer[] segment;
        int length = 0;
        if (head == null) {
            for (int i = 0; i < k; i++) {
                res.add(null);
            }
            return res.toArray(new ListNode[0]);
        }
        while (head != null) {
            head = head.next;
            length++;
        }
        if (k >= length) {
            //每个节点一个单独的list
            while (root != null) {
                res.add(new ListNode(root.val));
                root = root.next;
            }
            while (res.size() < k) {
                res.add(null);  //填充null节点进不足的分段
            }
        } else {
            //需要计算每个段长度:
            segment = this.countSeg(length, k, length / k);
            Arrays.sort(segment, (o1, o2) -> o2 - o1);
            System.out.println(Arrays.toString(segment));
            ListNode segHead = null;
            ListNode tail = null;
            for (int i : segment) {
                for (int cursor = 1; cursor <= i; cursor++) {
                    if (root == null) {
                        return res.toArray(new ListNode[0]);
                    }
                    if (cursor == i) {
                        if (segHead == null) {
                            segHead = new ListNode(root.val);   //这里可能有点问题
                            res.add(segHead);
                            segHead = null;
                            tail = null;
                        } else {
                            tail.next = new ListNode(root.val);
                            res.add(segHead);
                            segHead = null;
                            tail = null;
                        }
                    } else {
                        if (segHead == null) {
                            segHead = new ListNode(root.val); //头结点
                            tail = segHead;
                        } else {
                            tail.next = new ListNode(root.val);
                            tail = tail.next;
                        }
                    }
                    root = root.next;
                }
            }
        }
        return res.toArray(new ListNode[0]);
    }

    //总感觉这个计算有问题
    private Integer[] countSeg(int length, int k, int init) {
        //计算合适的段长度 , 1:段长度必须递减 2:段长度的差值不能大于1,很明显,只能有两组值
        Integer[] seg = new Integer[k];
        int total = 0;
        int count = 1;
        while (count < k) {
            total += init;
            ++count;
        }
        if (total + init == length) {
            for (int i = 0; i < k; i++) {
                seg[i] = init;
            }
            return seg;
        } else if (total + init > length) {
            for (int i = 0; i < k - 1; i++) {
                seg[i] = init;
            }
            seg[k - 1] = init - 1;
            return seg;
        } else {
            if (total + init + 1 == length) {
                for (int i = 0; i < k - 1; i++) {
                    seg[i] = init;
                }
                seg[k - 1] = init + 1;
                return seg;
            }
            return countSeg(length, k, init + 1);
        }
    }
}
