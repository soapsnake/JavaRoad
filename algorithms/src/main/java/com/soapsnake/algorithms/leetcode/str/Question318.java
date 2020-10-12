package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Question318 {

    public static void main(String[] args) {
        Question318 question318 = new Question318();
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(question318.maxProduct2(words));
    }

    /**
     * Example 1:
     * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * Output: 16
     * Explanation: The two words can be "abcw", "xtfn".
     * <p>
     * Example 2:
     * Input: ["a","ab","abc","d","cd","bcd","abcd"]
     * Output: 4
     * Explanation: The two words can be "ab", "cd".
     * <p>
     * Example 3:
     * Input: ["a","aa","aaa","aaaa"]
     * Output: 0
     * Explanation: No such pair of words.
     */
    public int maxProduct(String[] words) {
        //返回长度的最大乘积，其实就是找数组中两个最长的无公共字符的字符串
        //可以暴力算，平方级的复杂度
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (this.valid(words[i], words[j])) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    private boolean valid(String word, String word1) {
        //如果word和word1不含公共字符，那么返回ture
        Map<Character, Integer> map = new HashMap<>();
        for (char i : word.toCharArray()) {
            map.put(i, 1);
        }

        for (char j : word1.toCharArray()) {
            if (map.containsKey(j)) {
//                System.out.println("word=" + word + " word1=" + word1 + " map = " + map);
                return false;
            }
        }
        return true;
    }

    //o(n)级别复杂度算法
    public int maxProduct2(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                //
                value[i] |= 1 << (tmp.charAt(j) - 'a');
                System.out.println("value[" + i + "]= " + value[i]);
            }
        }
        System.out.println("value= " + Arrays.toString(value));
        int maxProduct = 0;
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++) {
                if ((value[i] & value[j]) == 0 && (words[i].length() * words[j].length() > maxProduct))
                    maxProduct = words[i].length() * words[j].length();
            }
        return maxProduct;
    }
}
