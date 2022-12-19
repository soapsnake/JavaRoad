package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.list.ListNode;

import java.util.PriorityQueue;
import java.util.Stack;

public class WeeklyContext281 {


    public String repeatLimitedString2(String s, int repeatLimit) {
        PriorityQueue<Character> que = new PriorityQueue<>((a, b) -> b - a);
        Stack<Character> stack  = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int count  = 0;
        for (char c : s.toCharArray()) {
            que.add(c);
        }
        char prev = que.peek();
        while(!que.isEmpty()) {
            char cur = que.poll();
            if (cur != prev) {
                if (stack.isEmpty()) {
                    sb.append(cur);
                    prev = cur;
                    count++;
                } else {
                    sb.append(cur);
                    count = 0;
                    while(!stack.isEmpty() && count < repeatLimit) {
                        sb.append(stack.pop());
                        count++;
                    }
                }
            } else {
                if (count < repeatLimit) {
                    sb.append(cur);
                } else {
                    stack.add(cur);
                }
                count++;
            }
        }
        return sb.toString();
    }


    //
    public int countEven(int num) {
        int cnt = 0;
        if (num < 2) {
            return 0;
        }
        for (int i = 2; i <= num ;i++) {
            if (i < 10) {
                if (i % 2 == 0) {
                    cnt++;
                }  
            } else {
                int temp = 0;
                while (i > 10) {
                    i = i / 10;
                    temp += i;
                }
                if (temp % 2 == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public static ListNode mergeNodes(ListNode head) {
        //1.把所有的0节点全部去掉
        //2. 把非0节点的值加起来,直到遇到0
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode cur = head;
        ListNode prev = fakeHead;
        int tempTotal = 0;
        while (cur != null) {
            if (cur.val == 0) {
                if (tempTotal != 0) {
                    //需要生成新节点,挂到之前的节点上.
                    ListNode tempNode = new ListNode(tempTotal);
                    tempTotal = 0;
                    //移动prev指针
                    prev.next = tempNode;
                    prev = prev.next;
                }
            } else {
                tempTotal += cur.val;
                //这种情况下不能移动prev指针.
            }
            cur = cur.next;
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        //0,3,1,0,4,5,2,0   => 4,11
        ListNode noew = new ListNode(0).next(1).next(0).next(3).next(0).next(2).next(2).next(0);
        System.out.println(mergeNodes(noew));

        /**
         * 通过默认构造函数实现的是一个小顶堆,means堆顶部的元素一定是所有元素的里面的最小值.
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);  //写了这个比较器之后,就变成了一个大顶堆
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
            System.out.println(queue.peek());
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }


    public long coutPairs(int[] nums, int k) {
        //第一种情况, nums[i] 本来就能被k整除

        int can = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % k == 0) {
                can++;
            }
        }
        if (can == 0) {
            return 0;
        }
        int total = can * (nums.length - 1) - (can -1);
        return total;
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        //构造大顶堆, 堆顶部元素为最大的数字
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> b - a);
               for(char ch:s.toCharArray()){
                   pq.add(ch);
               }
               //这步完结之后,pq.peek取到的就是最小的一个char了
               StringBuffer res = new StringBuffer();
               Stack<Character> stk = new Stack<>();
               int count = 0;
               char previouschar = pq.peek();  //初始值为最大的一个字符,通常就是z
               while(!pq.isEmpty()){
                   char curr = pq.poll();  //大顶堆弹出的就是最大的char
                   if(curr==previouschar){
                       //如果从优先级队列中出来的字符和前一个字符相同
                       if(count<repeatLimit){
                           //如果字符数字没有超过限制,那就简单的追加在最后
                           res.append(curr);
                       } else{
                           //如果字符数字超过了限制,那就把放到栈中,最先放入的会最后弹出,最大的字符会最后出来????
                           stk.add(curr);
                       }
                       count++;
                    } else {
                        //优先级出来的字符和前一个不相同
                       if(stk.isEmpty()){
                           //如果栈是空的,没有需要追加的
                           count=0;  //重置count
                           res.append(curr);  //直接追加
                           previouschar = curr;  //prev
                           count++;  
                       } else{
                           //如果栈里面有字符
                           res.append(curr);  //先追加新字符
                           count=0;
                           while(!stk.isEmpty() && count<repeatLimit){
                               res.append(stk.pop());
                               count++;
                           }
                       }
                   }
               }
               return res.toString();
           }

    //public long countPairs(int[] nums, int k) {
    //    long result = 0;
    //    Map<Integer, Integer> gcdMap = new HashMap<>();
    //    for (int i = 0; i < nums.length; i++) {
    //        int gcd = findGcd(nums[i], k);
    //        for (int num : gcdMap.keySet()) {
    //            if(gcd * num % k  == 0) {
    //                result += gcdMap.get(num);
    //            }
    //        }
    //        gcdMap.put(gcd, gcdMap.getOrDefault(gcd, 0) + 1);
    //    }
    //    return result;
    //
    //}
    
    


}