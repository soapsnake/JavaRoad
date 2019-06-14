package com.soapsnake.algorithms.structures.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap implements Cache {

    private Map<Integer, Integer> map = new LinkedHashMap<>();
    private int limit;
    public LRUCacheLinkedHashMap(int capacity) {
        this.limit = capacity;
    }

    @Override
    public int get(int key) {
        int res = -1;
        Integer tar = map.get(key);
        if (tar == null) {
            return res;
        } else {
            res = tar;
            map.remove(key);
            map.put(key, res);
            return res;
        }
    }


    @Override
    public void put(int key, int value) {
        //todo LinkedHashMap没有好的办法取到第一个和最后一个元素,所以不可取
        map.put(key, value);
        if (map.size() > limit) {
            for (Map.Entry entry: map.entrySet()) {
                System.out.println(entry);
            }
        }
    }
}
