package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-20
 */
public class Question395 {

    /**
     * 返回一个子字符串的长度,这个字符串要满足的条件:
     * 1. 子字符串是一个s的连续子字符串
     * 2. 子字符串中的每一个字符都要出现至少k次(>=k)
     * 这道题目难度很大
     */
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
            //问题1,为什么非要循环26次?? 这个循环的含义是,在每一次运算过程中,最多只允许出现numUniqueTarget个不重复的字符
            max = Math.max(max, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));

        return max;
    }

    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        //问题2:这个变量的含义是什么?这个是最关键的一个变量了!!!!
        //这个变量的含义是,在本轮次的遍历中,出现的独立字符的个数,比如abb,独立字符数就是2,abbc,独立字符数就是3
        int numUnique = 0; // counter 1
        //问题3:这个变量的含义是什么?
        int numNoLessThanK = 0; // counter 2
        int begin = 0, end = 0;
        int max = 0;

        while (end < s.length()) {
            //如果end指针指向的字符以前没有出现过,那么numUnique加一次,该字符的count数量也加1
            if (map[s.charAt(end)]++ == 0) numUnique++; // increment map[c] after this statement

            //如果end指针指向的字符已经出现了k次了,那么不小于k的变量值加1
            if (map[s.charAt(end++)] == k) numNoLessThanK++; // inc end after this statement

            //独立字符个数超过了允许出现的不重复字符个数,比如,第一轮numUniqueTarget=1,如果字符串是aab,
            //就是独立字符个数为2,就超过了1,这时候需要进行处理,那么如何处理了?
            while (numUnique > numUniqueTarget) {
                //如果begin指针指向的字符出现了k次,那么不小于k的变量值减1
                if (map[s.charAt(begin)]-- == k) numNoLessThanK--; // decrement map[c] after this statement

                //如果begin指针指向的字符此时出现次数为0,那么独立字符数量减去1
                if (map[s.charAt(begin++)] == 0) numUnique--; // inc begin after this statement
            }
            //如果来到这里,我们的子字符串最终只会出现numUniqueTarget个独立字符,如果是3个独立字符,每个要求出现3次,比如aaabbbccc
            // if we found a string where the number of unique chars equals our target
            // and all those chars are repeated at least K times then update max
            if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                max = Math.max(end - begin, max);
        }
        return max;
    }

    public int longestSubstring2(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        //变量h的含义是本轮最多只允许h个独立字符
        int h, i, j, idx, max = 0, unique, noLessThanK;

        //最多只允许出现26个独立字符,因为a ~ z只有26个
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0;
            j = 0;
            unique = 0;  //本轮次独立字符个数
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    //独立字符数超过了允许的个数,比如,本轮只允许3个独立字符,那么aabcdd就是不合法的,因为它有4个独立字符
                    //这段代码的含义是:如果本轮只允许h个独立字符,那么一旦独立字符数超过了h,记在超限的独立字符头上的重复次数
                    //就会被回收,直到该子字符串中只有h个独立字符为止,也就是我只计算在h个独立字符的前提下的重复次数
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }

        return max;
    }

    //递归法,这个是最快的
    public int longestSubstring3(String s, int k) {
        return longestSubstring(s, k, 0, s.length() - 1);
    }

    private int longestSubstring(String s, int k, int start, int end) {
        if (start > end) {
            return 0;
        }

        int[] counts = new int[26];
        for (int i  = start; i <= end; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        boolean noSplits = true;
        int result = 0;
        int prevStart = start;
        for (int i  = start; i <= end; i++) {
            if (counts[s.charAt(i) - 'a'] < k && counts[s.charAt(i) - 'a'] > 0) {
                noSplits = false;
                result = Math.max(result, longestSubstring(s, k, prevStart, i - 1));
                prevStart = i + 1;
            }
        }

        if (noSplits) {
            return end - start + 1;
        }

        return Math.max(result, longestSubstring(s, k, prevStart, end));

    }

    public static void main(String[] args) {
        Question395 question395 = new Question395();
        String s = "abcdef";
        int k = 2;
        System.out.println(question395.longestSubstring(s, k));
    }
}
