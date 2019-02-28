package com.soapsnake.algorithms.leetcode.list;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-28 14:44
 */
public class Question92 {

    private int code;

    /**
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     */
    //这个实现过于复杂!!!!!!
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //翻转链表中的指定几个节点

        /**
         * 思路:三个区间拼装     头    -------未翻转区------->-------翻转区-------->----未翻转区---->   尾
         */
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int start = 1;
        ListNode newhead = null;
        ListNode brokeleft = head;   //起始翻转的前一个节点需要保存,因为反转完后需要重新接上
        while (cur != null && start <= n) {
            ListNode next = cur.next;
            if (start < m - 1) {
                brokeleft = brokeleft.next;
            }
            if (start >= m  && start <= n) {
                cur.next = newhead;
                newhead = cur;
            }
            cur = next;
            start++;
        }
        ListNode newcusor = newhead;
        if (m > 1 && brokeleft != null) {
            brokeleft.next = newhead;  //拼装未翻转节点
        }
        if (cur != null) {          //翻转结束后的第一个节点需要保留,因为需要在最后拼回去
            while (newcusor.next != null) {
                newcusor = newcusor.next;   //翻转后的链表需要遍历一下到表尾,这个地方有优化空间
            }
            newcusor.next = cur;   //拼装cur节点
        }
        if (m > 1) {
            return head;
        } else {
            return newhead;
        }
    }

    public static void main(String[] args) {
        Question92 question92 = new Question92();
        ListNode head = ListNode.makeTestListFor725();   //1 -> 2->3->4->5->6->7->8


//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
        System.out.println(question92.reverseBetween(head , 3, 5));  //1-->2->5->4->3->6->7->8
    }
}
