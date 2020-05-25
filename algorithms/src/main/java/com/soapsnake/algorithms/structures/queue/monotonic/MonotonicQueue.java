package com.soapsnake.algorithms.structures.queue.monotonic;

/**
 *
 * Created on 2020-05-19
 *
 * 单调队列的两个限制条件:
 * 1.为了维护队列内元素的「单调」性，所有要入队的元素，统一从队尾入队，再从对首出队，也可以从对尾直接出队。
 * 2.单调队列的长度有固定限制,如果长度超过了固定限制,应该弹出最小值
 */
public interface MonotonicQueue<T> {
    /**
     *
     * 以单调递增队列为例: 元素入队时比较元素和队尾元素的值,如果新元素更小,那么挨个弹出队尾,直到新元素小于队尾为止,然后入队新元素至队尾
     * 如果新元素比原队尾大那么挨个往前直到到达自己的准确位置
     */
    void push(T newItem);

    /**
     * @return the first value of the queue, which is usually a maximum or a
     * minimum.
     * 获取队头,队头的元素一般都是最大/最小的元素,有点像优先级队列,但是这个队列是严格排序的
     */
    T getFirst();

    /**
     * Removes min or max when it is no longer needed.
     * 删除队头
     */
    void removeFirst();
}

