package com.soapsnake.algorithms.structures.queue.priority;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

public class Multiway {
    public static void merge(String[] streams) {
        int N = streams.length;
        MaxPriorityQueue<String> queue = new MaxPriorityQueue<>(N);
        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()) {
                queue.insert(streams[i]);  //往pq插入一个新的元素
            }
        }
        int i = 0;
        while (!queue.isEmpty()) {
            System.out.println(queue.max());
//            int i = pq.delMax();   //删除堆顶元素,最小元素,i一定是最小元素的索引,那么这个i一直是同一个值?
            if (!streams[i++].isEmpty()) {
                queue.insert(streams[i]);  //把一个元素插到堆顶会触发sink(),数字会下沉至合适位置
            }
        }
    }

    public static void main(String[] args) {
        int N = 10;
        String[] streams = new String[N];
        for (int i = 0; i < N; i++) {
            streams[i] = RandomStringUtils.randomAlphabetic(10);
        }
        System.out.println("排序前:streams = " + Arrays.toString(streams));
        merge(streams);
        System.out.println("排序后:streams = " + Arrays.toString(streams));
    }
}
