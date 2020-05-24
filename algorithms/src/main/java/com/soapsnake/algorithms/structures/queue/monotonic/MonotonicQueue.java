package com.soapsnake.algorithms.structures.queue.monotonic;

/**
 *
 * Created on 2020-05-19
 *
 * 单调队列,队列内的元素都是单调递增或者/递减的
 */
public interface MonotonicQueue<T> {
    /**
     * Usually removes elements from queue compared to value in {@param T} to
     * preserve monotonicity. Then adds a new element.
     */
    void push(T newItem);

    /**
     * @return the first value of the queue, which is usually a maximum or a
     * minimum.
     * 获取队头,队头的元素一般都是最大/最小的元素,有点像优先级队列,但是这个队列是严格排序的
     */
    int getFirst();

    /**
     * Removes min or max when it is no longer needed.
     * 删除队头
     */
    void removeFirst();
}

