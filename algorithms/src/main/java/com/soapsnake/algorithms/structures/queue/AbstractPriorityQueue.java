package com.soapsnake.algorithms.structures.queue;

public abstract class AbstractPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {

    protected T[] pq;

    final public boolean lessThan(int i1, int i2) {
        if (null == pq[i1]) {
            return false;
        }
        System.out.println(i1 + "," + i2);
        int res = pq[i1].compareTo(pq[i2]);
        return res < 0;
    }

    final public void exchange(int i1, int i2) {
        T temp = pq[i1];
        pq[i1] = pq[i2];
        pq[i2] = temp;
    }
}
