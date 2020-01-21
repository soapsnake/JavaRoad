package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;

/**
 * @author liudun <liudun@kuaishou.com>
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
        int sPoint = 0;
        for (int tPoint = 0; tPoint < t.length(); ) {
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
}
