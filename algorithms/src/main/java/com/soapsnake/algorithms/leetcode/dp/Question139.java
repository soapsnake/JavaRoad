package com.soapsnake.algorithms.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-27 19:07
 */
public class Question139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (breakable[j] && wordDict.contains(s.substring(j, i))) {  //两个条件: 1.j之前的子串是满足的, 2.j-i是满足的,同时满足两个条件i处才能标true
                    breakable[i] = true;
                    break;
                }
            }
        }
        return breakable[s.length()];
    }

    //复杂度过高
    public boolean wordBreak2(String s, List<String> wordDict) {

        if (s == null) {
            return true;
        }

        backtrace(s, wordDict);
        return valid;
    }

    boolean valid = false;

    private void backtrace(String s, List<String> wordDict) {
        System.out.println(s);
        if (s.length() == 0) {
            valid = true;
        } else {
            for (String str : wordDict) {
                if (str.charAt(0) == s.charAt(0)) {
                    if (s.length() >= str.length() && s.substring(0, str.length()).equals(str)) {
                        backtrace(s.substring(str.length()), wordDict);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Question139 question139 = new Question139();
        String s = "ccbb";
        List<String> list = Arrays.asList("bc", "cb");
        System.out.println(question139.wordBreak(s, list));
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        if (null == s) {
            return true;
        }

        boolean[] match = new boolean[s.length()];
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (match[j] && wordDict.contains(s.substring(j , i))) {
                    match[i] = true;
                    break;
                }
            }
        }
        return match[s.length() - 1];
    }
}