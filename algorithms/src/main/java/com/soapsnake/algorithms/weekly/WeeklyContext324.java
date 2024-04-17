package com.soapsnake.algorithms.weekly;

import java.util.HashSet;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-12-18
 * JavaRoad
 */
public class WeeklyContext324 {

    public int similarPairs(String[] words) {
        int n = words.length;
        Set<String> res = new HashSet<>();
        int ans =  0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (like(words, i, j)) {
                    if (res.add(i + "->" + j)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    private static boolean like(String[] words, int i, int j) {
        String a = words[i];;
        String b = words[j];
        Set<String> s1 = add(a);
        Set<String> s2 = add(b);
        return s1.equals(s2);
    }

    private static Set<String> add(String a) {
        Set<String> res = new HashSet<>();
        for (char c : a.toCharArray()) {
            res.add(String.valueOf(c));
        }
        return res;
    }
}
