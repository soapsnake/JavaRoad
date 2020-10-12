package com.soapsnake.algorithms.structures.queue.priority;


/**
 * 优先级队列
 */
public interface PriorityQueue<T extends Comparable<T>> {

    /**
     * 往优先级队列插入数据,没满插入,满则和堆顶比较是要插入还是丢弃
     * @param t
     */
    void insert(T t);

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 当前容量
     * @return
     */
    int size();

    /**
     * 输出排序后的结果
     * @return
     */
    T[] sort();
}
