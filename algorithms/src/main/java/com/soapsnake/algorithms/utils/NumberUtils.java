package com.soapsnake.algorithms.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class NumberUtils {

    public static Collection<Integer> batchGen(int size) {
        Collection<Integer> collection = new ArrayList<>(size);
        while (collection.size() < size) {
           int number =  RandomUtils.nextInt(1,100);
           collection.add(number);
        }
        return collection;
    }

    /**
     * 生成不重复的数字
     * @param size
     * @return
     */
    public static Collection<Integer> batchGenNonDup(int size) {
        Collection<Integer> collection = new HashSet<>(size);
        while (collection.size() < size) {
            int number =  RandomUtils.nextInt(1,100);
            collection.add(number);
        }
        return collection;
    }



}
