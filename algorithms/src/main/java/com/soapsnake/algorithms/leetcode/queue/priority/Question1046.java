package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.PriorityQueue;

import com.soapsnake.algorithms.alib.Person;

public class Question1046 {

    //leetcode1046
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
        for (int a : stones)
            pq.offer(a);
        while (pq.size() > 1)
            //每次把优先级队列里面最大的两个数字弹出来,做减法后把其差再塞回队列
            //注意,弹出数字后队列的size会减小,因为每一轮都是弹出两个,塞回去一个
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
    }

    public static void main(String[] args) {
        String str = "12321.1";
        System.out.println(Double.parseDouble(str));
        Person person = new Person();
        long originCount = 1823784732;
        double count = 123.432;
        long finalCount = (long) (originCount - count);   //原内存量 - 划走的内存量 = 调整后内存量
        System.out.println(finalCount);

    }
}
