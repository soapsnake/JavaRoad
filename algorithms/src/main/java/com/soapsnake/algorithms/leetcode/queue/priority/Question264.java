package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Question264 {

    public static void main(String[] args) {
        Question264 question264 = new Question264();
        int n = 10;
        System.out.println(question264.nthUglyNumber2(n));
    }

    /**
     * The basic idea of this problem is to compute all the ugly numbers in sequence and count to the given number of k ugly numbers.
     * The way I approached this problem is first I have a arraylist to store the ugly numbers in sequence.
     * Then I declared three counter variables: a,b,and c which represent the corresponding index in the arraylist for the multiplier of 2,3,and 5.
     * Since each previous ugly number times one of the multiplier will produce a new ugly number,
     * I start from the starting index 0 and multiply the ugly number at that index with each multiplier and get the smallest product which is the next ugly number from the three.
     * The corresponding multipliers' index will be incremented by one and we do this recursively until we have K ugly numbers.
     * Here is the code implementation in Java:
     */
    public int nthUglyNumber3(int n) {
        if (n <= 0) return 0;
        int a = 0, b = 0, c = 0;
        List<Integer> table = new ArrayList<Integer>();
        table.add(1);
        while (table.size() < n) {
            int next_val = Math.min(table.get(a) * 2, Math.min(table.get(b) * 3, table.get(c) * 5));
            table.add(next_val);
            if (table.get(a) * 2 == next_val) a++;   //如果索引a处的元素,乘以2等于最小数,那么a索引加1
            if (table.get(b) * 3 == next_val) b++;
            if (table.get(c) * 5 == next_val) c++;
        }
        return table.get(table.size() - 1);
    }

    //2, 3, 5
    //leetcode264
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if (factor2 == min)
                factor2 = 2 * ugly[++index2];
            if (factor3 == min)
                factor3 = 3 * ugly[++index3];
            if (factor5 == min)
                factor5 = 5 * ugly[++index5];
        }
        return ugly[n - 1];
    }

    public int nthUglyNumber2(int n) {
        if (n == 1) return 1;
        PriorityQueue<Long> q = new PriorityQueue<>();   //Jdk的这个优先级队列是一个小顶堆,堆顶元素最小,也就是queue[0]是最小值
        q.add(1L);

        List<Long> out = new ArrayList<>();
        for (long i = 1; i < n; i++) {
            System.out.println("操作前的队列= " + q);
            long tmp = q.poll();   //弹出堆顶最小元素,是remove操作,弹出堆顶后,pq会把queue[]的最后一个元素插到堆顶,然后下沉这个新堆顶,保证堆顶一定最小
            out.add(tmp);
            while (!q.isEmpty() && q.peek() == tmp) {   //这一步不明白是干嘛的了,去除重复元素吗????
                tmp = q.poll();
                out.add(tmp);
            }
            q.add(tmp * 2);
            q.add(tmp * 3);
            q.add(tmp * 5);
            System.out.println("操作结束的队列= " + q);
        }
        System.out.println("out = " + out);
        return q.poll().intValue();
    }


}
