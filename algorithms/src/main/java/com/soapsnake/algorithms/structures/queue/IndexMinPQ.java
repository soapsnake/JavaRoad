package com.soapsnake.algorithms.structures.queue;


/**
 * 1. 小顶堆(堆顶部元素最小)
 * 2. 带索引
 *
 * @param <K>
 */
public class IndexMinPQ<K extends Comparable<K>> implements PriorityQueue<K> {

    // create a priority queue of capacity maxNwith possible indices between 0 and maxN-1
    IndexMinPQ(int maxN) {

    }

    //insert item ; associate it with k
    //讲元素插到索引k处,实际上这是一个替换操作,但是插入完了次序可能是乱的????
    void insert(int k, K item) {

    }

    //change the item associated with k to item
    void change(int k, K item) {

    }

    //is k associated with some item?
    //k索引处是否有元素
    boolean contains(int k) {
        return false;
    }

    //remove k and its associated itemItem
    //删除索引k的元素
    void delete(int k) {

    }

    //return a minimal itemint
    int min() {
        return 0;
    }

    //return a minimal item’s index
    int minIndex() {
        return 0;
    }


    //remove a minimal item and return its indexboolean,return its index!!!!!!
    //删除栈顶元素会触发上浮操作
    int delMin() {
        return 0;
    }

    @Override
    //插入元素先固定把元素插入叶子,然后视情况触发swim()上浮
    public void insert(K t) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    //number of items in the priority queue
    @Override
    public int size() {
        return 0;
    }

    @Override
    public K[] sort() {
        return null;
    }
}
