package com.soapsnake.algorithms.structures.cache;

public interface Cache<K, V> {

    int get(int key);

    void put(int key, int value);
}
