package com.soapsnake.algorithms.datastructures.queue;

public class MaxPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K>{

    int size;

    int cap;

    MaxPriorityQueue() {

    }

    /**
     * create a priority queue of initial capacity max
     * @param max
     */
    MaxPriorityQueue(int max) {}

    /**
     * create a priority queue from the keys in a[]
     * @param a
     */
    MaxPriorityQueue(K[] a) {}

    /**
     * return and remove the largest key
     */
    K delMax() {
        return null;
    }

    @Override
    public void insert(K k) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }



}
