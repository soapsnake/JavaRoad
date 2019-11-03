package com.soapsnake.algorithms.structures.queue;


/**
 * 优先级队列
 */
public interface PriorityQueue<T extends Comparable<T>> {

    /**
     * 往优先级队列插入数据
     *
     * @param t
     */
    void insert(T t);


    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 当前容量
     *
     * @return
     */
    int size();

    /**
     * topK问题所用接口,如果超过了容量,再向堆中添加数据,就需要和堆顶比较,这里
     * 最小堆和最大堆是不一样的,需要分情况
     * @param t
     */
    void addMore(T t);
}
