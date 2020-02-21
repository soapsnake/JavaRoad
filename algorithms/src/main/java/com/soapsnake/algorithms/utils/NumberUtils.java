package com.soapsnake.algorithms.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

public class NumberUtils {

    public static Collection<Integer> batchGen(int size) {
        Collection<Integer> collection = new ArrayList<>(size);
        while (collection.size() < size) {
            int number = RandomUtils.nextInt(1, 100);
            collection.add(number);
        }
        return collection;
    }

    /**
     * 生成不重复的数字
     *
     * @param size
     * @return
     */
    public static List<Integer> batchGenNonDup(int size) {
        Collection<Integer> collection = new HashSet<>(size);
        while (collection.size() < size) {
            int number = RandomUtils.nextInt(1, 100);
            collection.add(number);
        }
        return new ArrayList<>(collection);
    }


}
