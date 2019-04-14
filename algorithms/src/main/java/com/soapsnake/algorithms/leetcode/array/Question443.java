package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 20:55
 */
public class Question443 {

    //你能看懂这个算法的逻辑我就拜你为师!!

    public static void main(String[] args) {
        Question443 question443 = new Question443();
        char[] chars = {'a'};
        System.out.println(question443.compress(chars));
    }

    /**
     * Input:
     * ["a","a","b","b","c","c","c"]
     * <p>
     * Output:
     * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
     */
    public int compress(char[] chars) {
        //思路:对于每一个chars[i],统计其重复数,如果重复数小于10,那么index加1位,如果大于10,那么重复数转字符串,index加该字符串的长度

        if (chars == null || chars.length == 0)
            return 0;

        int index = 0, n = chars.length, i = 0;
        while (i < n) {
            char ch = chars[i];
            int j = i;
            while (j < n && chars[i] == chars[j]) { // chars[i..j - 1] are ch.
                j++;
            }
            int freq = j - i; // The frequency of ch.
            chars[index++] = ch;
            if (freq == 1) {

            }
            else if (freq < 10) {
                chars[index++] = (char)(freq + '0');
            }
            else {
                String strFreq = String.valueOf(freq);
                for (char chFreq : strFreq.toCharArray())
                    chars[index++] = chFreq;
            }
            i = j;
        }
        return index;
    }

    //这个解法的问题是只考虑了字符重复个位数(小于10)的情况,如果遇到重复数大于10次的就会出现问题
    public int compress2(char[] chars) {
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
        return map.size() * 2;
    }
}
