package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import sun.jvm.hotspot.jdi.PrimitiveValueImpl;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-19
 */
public class Question380 {

    static class RandomizedSet {
        private Map<Integer, Integer> map;   //val -> index
        private Random random;
        private List<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            this.list = new ArrayList<>();
            this.random = new Random();
            this.map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean res = false;
            if (map.get(val) == null) {
                res = true;
                list.add(val);
                map.put(val, list.size() - 1);
            }
            return res;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean res = false;
            if (map.get(val) != null) {
                res = true;
                int index = map.get(val);
                if (index < list.size()) {  //如果移除的元素不是最后一个元素,那么把要移除的元素交换到list尾部
                    int last = list.get(list.size() - 1);
                    list.set(index, last);
                    map.put(last, index);   //最后一个元素的索引发生了变化
                    list.remove(list.size() - 1);
                    map.remove(val);
                }
            }
            return res;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(10));
        System.out.println(randomizedSet.getRandom());
    }
}
