package com.soapsnake.algorithms.leetcode.biner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-13
 */
public class Question1461 {

    //leetcode1461
    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        if (s.length() - k < 0) {
            return false;
        }
        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
        }
        return set.size() == Math.pow(2, k);
    }
}
