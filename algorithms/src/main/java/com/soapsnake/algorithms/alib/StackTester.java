package com.soapsnake.algorithms.alib;

import java.util.Stack;

import org.junit.Test;

import com.soapsnake.algorithms.structures.list.ListNode;

public class StackTester {

    /**
     * 题目：输入两个整数序列。其中一个序列表示栈的push顺序，
     * 判断另一个序列有没有可能是对应的pop顺序。
     * 为了简单起见，我们假设push序列的任意两个整数都是不相等的。
     *
     *
     * 比如输入的push序列是1,2、3、4、5，那么4、5、3、2、1就有可能是一个pop系列。
     * 因为可以有如下的push和pop序列：
     * push 1，push 2，push 3，push 4，pop，push 5，pop，pop，pop，pop，
     * 这样得到的pop序列就是4、5、3、2、1。
     * 但序列4、3、5、1、2就不可能是push序列1、2、3、4、5的pop序列。
     */
    public boolean validPopSequence2(int[] pushs, int[] pops) {
        if (pushs.length != pops.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while (i < pushs.length) {
            while (i < pushs.length) {
                stack.push(pushs[i++]);
                if (pushs[i - 1] == pops[j]) {  //第一次break的时候j == 0,pushs中的元素必须不一样
                    break;
                }
            }

            //到此,发现pushs[i] == pops[j],那么继续有两种选择:1.继续弹栈看是否和pops一致 2.不一致,那么把元素塞回去,继续下一轮
            while (j < pops.length) {
                int top = stack.pop();
                if (top == pops[j]) {
                    j++;
                } else {
                    stack.push(top);
                    break;
                }
            }
        }
        return stack.isEmpty();
    }


    @Test
    public void testValidPopSequence() {
        int[] pushs = {1, 2, 3, 4, 5};
        int[] pops = {4, 3, 5, 1, 2};
        System.out.println(validPopSequence2(pushs, pops));
    }

    public ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
        // write code here
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode pointer = fakeHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                pointer.next = head1;
                head1 = head1.next;
            } else {
                pointer.next = head2;
                head2 = head2.next;
            }
            pointer = pointer.next;
        }
        pointer.next = head1 == null ? head2 : head1;
        return fakeHead.next;
    }

}
