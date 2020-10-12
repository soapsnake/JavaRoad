package com.soapsnake.algorithms.structures.set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-03 20:27
 */
public class MyHashSet {

    int[] arr;
    private static final int SIZE = 1000000;
    public MyHashSet() {
        arr = new int[SIZE];
    }

    public void add(int key) {
        arr[key] = 1;
    }

    public void remove(int key) {
        arr[key] = 0;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return arr[key] == 1;
    }

}
