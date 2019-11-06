package com.soapsnake.algorithms.alib;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class StringTester {

    /**
     * 翻转句子中单词的顺序。
     * 题目：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
     * 句子中单词以空格符隔开。为简单起见，标点符号和普通字母一样处理。
     * 例如输入“I am a student.”，则输出“student. a am I”。
     */
    public String revertWords(String source) {
        if (source == null || "".equals(source)) {
            return null;
        }
        String[] strings = source.split(" ");
        int len = strings.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            stringBuilder.append(strings[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Test
    public void testRevertWords() {
        String s = "I am a student.";
        System.out.println(revertWords(s));
    }

    /**
     * 题目：在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
     * 分析：这道题是2006年google的一道笔试题。
     */
    public Character findFirst(String source) {
        if (source == null) {
            return null;
        }
        int len = source.length();
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            map.put(source.charAt(i), map.getOrDefault(source.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Test
    public void testFindFirst() {
        String source = "abacbccdeff";
        System.out.println(findFirst(source));
    }


    /**
     * 题目：
     * 定义字符串的左旋转操作：把字符串前面的若干个字符移动到字符串的尾部。
     * 如把字符串abcdef左旋转2位得到字符串cdefab。请实现字符串左旋转的函数。
     * 要求时间对长度为n的字符串操作的复杂度为O(n)，辅助内存为O(1)。
     */
    public String leftVer(String source, int n) {
        if (source == null) {
            return source;
        }
        //abcdef ->  cdefab
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(source, 0, n);
        stringBuilder.insert(0,source.substring(n));
        return stringBuilder.toString();
    }

    @Test
    public void testLeftVer() {
        String source = "abcdef";
        int n = 2;
        System.out.println(leftVer(source, n));
    }
}
