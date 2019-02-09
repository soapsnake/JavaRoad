package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 20:55
 */
public class Question443 {

    //你能看懂这个算法的逻辑我就拜你为师!!
    public int compress(char[] chars) {
        if (chars == null) {
            return 0;
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
        return 2 * map.size();
    }

    public static void main(String[] args) {
        Question443 question443 = new Question443();
        char[] chars = {'a'};
        System.out.println(question443.compress(chars));
    }
}
