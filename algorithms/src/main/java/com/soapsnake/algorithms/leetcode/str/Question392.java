package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created on 2020-01-20
 */
public class Question392 {

    /**
     * Example 1:
     * s = "abc", t = "ahbgdc"
     *return true
     *
     * 本人o(n)时间复杂度版
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.equals("")) {
            return true;
        }
        for (int tPoint = 0, sPoint = 0; tPoint < t.length(); ) {
            if (s.charAt(sPoint) == t.charAt(tPoint)) {
                if (sPoint == s.length() - 1) {
                    return true;
                }
                sPoint++;
            }
            tPoint++;
        }
        return false;
    }

    public boolean isSubsequence3(String s, String t) {
        if (s.isEmpty()) return true;
        int s_idx = 0, t_idx = 0;
        int idx_val = s.charAt(0);
        while (t_idx < t.length()) {
            int idx = t.indexOf(idx_val, t_idx) ;
            if (idx == -1) return false;  //s的第一个字符t中都不存在
            else t_idx = idx + 1;  //idx是相对于字串t来说的,相当于t指针往右移动
            s_idx++; //s指针移动
            if (s_idx >= s.length()) return true;
            idx_val = s.charAt(s_idx);  //指向了s的第二个待匹配字符
        }

        return false;
    }

    //I think the Map and TreeSet could be simplified by Array and binarySearch. Since we scan T from beginning to the end (index itself is in increasing order), List will be sufficient. Then we can use binarySearch to replace with TreeSet ability which is a little overkill for this problem. Here is my solution.
    // Follow-up: O(N) time for pre-processing, O(Mlog?) for each S.
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    //  i=2 ('c'): prev=? (return false)
    // 大神二分查找法
    public boolean isSubsequence2(String s, String t) {
        List<Integer>[] idx = new List[256]; // Just for clarity  hashmap结构
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i)] == null)
                idx[t.charAt(i)] = new ArrayList<>();
            idx[t.charAt(i)].add(i);
        }

        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i)] == null) {
                return false; // Note: char of S does NOT exist in T causing NPE
            }
            int j = Collections.binarySearch(idx[s.charAt(i)], prev);
            if (j < 0) j = -j - 1;
            if (j == idx[s.charAt(i)].size()) return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
}
