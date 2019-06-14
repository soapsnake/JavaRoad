package com.soapsnake.algorithms.structures.cache;

public class CacheTest {

    public static void main(String[] args) {
        Cache cache = new LRUCacheList(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(2));       // returns 3
    }
}
