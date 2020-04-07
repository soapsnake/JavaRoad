package com.soapsnake.algorithms.leetcode.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 *
 * Created on 2020-01-20
 */
public class Question382 {

    static class Solution {
        List<ListNode> lists;
        Random random = new Random();

        ListNode sourceHead;

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            this.sourceHead = head;
            this.lists = new ArrayList<>();
            ListNode cur = head;
            while (null != cur) {
                lists.add(cur);
                cur = cur.next;
            }
        }

        /** Returns a random node's value. */
        public int getRandom() {
            return this.lists.get(random.nextInt(lists.size())).val;
        }

        public int getRandom2() {
            ListNode c = sourceHead;
            int r = c.val;
            for(int i=1;c.next != null;i++){
                c = c.next;
                if(random.nextInt(i + 1) == i) { //概率 1 / i + 1
                    //注意了,最关键的就是这里没有break!没有break!没有break!!!!也就是说r的值有可能会变化很多次
                    //从0 -> i, r的值会被 1 / i + 1的概率更新i次,所以最后的概率也就是 1 / i + 1
                    r = c.val;
                }
            }
            return r;
        }
    }
}
