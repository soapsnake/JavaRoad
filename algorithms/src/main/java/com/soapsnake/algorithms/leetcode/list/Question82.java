package com.soapsnake.algorithms.leetcode.list;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-26 10:42
 */
public class Question82 {

    /**
     * 与83题的区别是,这个题目要求只要出现重复的就全部删除一个不留
     */
    public ListNode deleteDuplicates(ListNode head) {

        //傻逼级解法:复杂度平方级,每一个节点都扫描其后的每一个节点
        if (head == null) {
            return null;
        }

        ListNode fakehead = new ListNode(0);
        fakehead.next = head;
        ListNode cur = head;
        ListNode curpre = fakehead;
        boolean needdel = false;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode nextpre = cur;
            while (next != null) {
                if (next.val == cur.val) {
                    needdel = true;
                    nextpre.next = next.next;
                } else {
                    nextpre = nextpre.next;
                }
                next = next.next;
            }
            if (needdel) {
                curpre.next = cur.next;
            } else {
                curpre = curpre.next;
            }
            cur = cur.next;
            needdel = false;
        }
        return fakehead.next;
    }

    //o(n)复杂度删除链表重复节点(所有出现重复的节点全部删除)
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fakehead = new ListNode(0);
        fakehead.next = head;
        ListNode pre = fakehead;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                //发现了重复节点
                cur = cur.next;
            }
            //这个算法最核心的思想就是这个==号,object如果==为true表明这个节点没有变过(没有重复)
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fakehead.next;
    }


        public static void main(String[] args) {
        Question82 question82 = new Question82();
        ListNode head = ListNode.makeTestListFor82();
        System.out.println(question82.deleteDuplicates(head));  //should be 1 -> 2 -> 5 -> 8
    }
}
