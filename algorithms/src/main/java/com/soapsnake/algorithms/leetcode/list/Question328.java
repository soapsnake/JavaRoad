package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-22 20:51
 */
public class Question328 {

    /**
     * 题目理解错误: 把奇序号的所有节点放在一起,所有偶序号的节点放在一起,无视节点的值
     */
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null){
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = even.next;
        ListNode tempEven = even;
        int count = 3;
        while(temp!=null){
            if(count%2==0){
                even.next = temp;
                even=even.next;
            } else {
                odd.next = temp;
                odd = odd.next;
            }
            temp=temp.next;
            count++;
        }
        even.next = null;
        odd.next = tempEven;
        return head;
    }

    public static void main(String[] args) {
        Question328 question328 = new Question328();
ListNode head = ListNode.makeTestListFor725();
        System.out.println(question328.oddEvenList(head));
    }
}
