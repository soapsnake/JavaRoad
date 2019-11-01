package com.soapsnake.algorithms.structures.queue;


/**
 * 优先级队列
 */
public interface PriorityQueue<T> {

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


}
