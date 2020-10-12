package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 23:01
 */
public class Question14 {

    public static void main(String[] args) {
        Question14 question14 = new Question14();
        String[] strings = {"c", "c"};
        System.out.println(question14.longestCommonPrefix(strings));
    }

    //todo 二分查找思想
    public String longestCommonPrefix2(String[] strs) {

        return "";
    }

    public String longestCommonPrefix(String[] strs) {
        String maxPre = "";
        if (strs == null || strs.length == 0) {
            return maxPre;
        }

        String cur = strs[0];
        if (strs.length == 1) {
            return cur;
        }
        for (int i = 0; i < cur.length(); i++) {
            String pre = cur.substring(0, i + 1);
            for (String s : strs) {
                if (s.length() < pre.length()) {
                    return maxPre;
                }
                if (!s.substring(0, i + 1).equals(pre)) {
                    return maxPre;
                }
            }
            maxPre = pre;
        }
        return maxPre;
    }
}
