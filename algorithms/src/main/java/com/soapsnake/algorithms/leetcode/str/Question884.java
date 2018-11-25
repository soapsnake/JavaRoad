package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author soapsnake
 * @date 2018/10/31
 *
 * We are given two sentences A and B.  (A sentence is a string of space separated words.
 * Each word consists only of lowercase letters.)
 */
class Question884 {

    public String[] uncommonFromSentences(String A, String B) {
        String[] awords = A.split(" ");
        String[] bwords = B.split(" ");

        Map<String, Integer> map  = new HashMap<>();
        for (String s1 : awords) {
            if (map.containsKey(s1)) {
                map.put(s1, map.get(s1)+1 );
            } else {
                map.put(s1, 1);
            }
        }

        for (String s2 : bwords) {
            if (map.containsKey(s2)) {
                map.put(s2, map.get(s2)+1 );
            } else {
                map.put(s2, 1);
            }
        }
        List<String> res = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res.add(entry.getKey());
            }
        }
        return res.toArray(new String[0]);
    }

    //这个解法是错的,只有当重复单词只会重复一次时可以用这个解法
    public String[] uncommonFromSentences2(String A, String B) {
        List<String> list = new ArrayList<>();

        String[] awords = A.split(" ");
        String[] bwords = B.split(" ");

        for (String s : awords) {
            if (list.contains(s)) {
                list.remove(s);
            } else {
                list.add(s);
            }
        }

        for (String s : bwords) {
            if (list.contains(s)) {
                list.remove(s);
            } else {
                list.add(s);
            }
        }

        return list.toArray(new String[0]);
        }
    }
