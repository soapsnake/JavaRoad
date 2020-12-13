package com.soapsnake.algorithms.alib;

import com.sun.tools.javac.code.TargetType;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import javax.print.attribute.standard.Destination;
import javax.xml.ws.soap.Addressing;

import com.soapsnake.algorithms.structures.cache.LRUCache;

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
        stringBuilder.insert(0, source.substring(n));
        return stringBuilder.toString();
    }

    @Test
    public void testLeftVer() {
        String source = "abcdef";
        int n = 2;
        System.out.println(leftVer(source, n));
    }

    /**
     * 字符串转数字
     */
    public static int strToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        boolean positive = true;
        int i = 0;
        int len = str.length();
        long cal = 0;
        while (i < len) {
            if (i == 0) {
                if (str.charAt(i) == '+') {

                } else if (str.charAt(i) == '-') {
                    positive = false;
                } else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    return 0;
                } else {
                    cal = str.charAt(i) - '0';
                }
            } else {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    return 0;
                }
                cal = cal * 10 + (str.charAt(i) - '0');
                if (cal > Integer.MAX_VALUE) {
                    if (positive) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
            }
            i++;
        }
        if (positive) {
            return (int) cal;
        } else {
            return (int) -cal;
        }
    }

    public int longestCommonSubsequence(String text1, String text2) {
        //最长公共子串
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); ++i)
            for (int j = 0; j < text2.length(); ++j)
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[text1.length()][text2.length()];
    }

    @Test
    public void testIssubsequence() {
        System.out.println(isSubsequence("ba", "aab"));
    }

    public boolean isSubsequence(String s, String t) {
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
            if (j < 0)
                j = -j - 1;
            if (j == idx[s.charAt(i)].size())
                return false;
            prev = idx[s.charAt(i)].get(j) + 1;
        }
        return true;
    }

    @Test
    public void testCollectionsBinarySearch() {
        List<Integer> lls = new ArrayList<>();
        lls.add(3);
        lls.add(4);
        lls.add(5);
        lls.add(9);
        lls.add(123);

        System.out.println(Collections.binarySearch(lls, 3));
        System.out.println(Collections.binarySearch(lls, 5));
        System.out.println(Collections.binarySearch(lls, -1));
        System.out.println(Collections.binarySearch(lls, 100));
        System.out.println(Collections.binarySearch(lls, 123));
        System.out.println(Collections.binarySearch(lls, 1999));
        System.out.println(Collections.binarySearch(lls, -10000));
    }


    /**
     * Input: n = 4, k = 9
     * Output: "2314"
     */
    public String getPermutation(int n, int k) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return "1";
        }
        int[] source = new int[n];
        for (int i = 0; i < n; i++) {
            source[i] = i + 1;
        }
        Set<Integer> all = new HashSet<>();
        this.backTrace(source, 0, all, new ArrayList<>());
        List<Integer> alllList = new ArrayList<>(all);
        alllList.sort(Integer::compareTo);
        return String.valueOf(alllList.get(k - 1));
    }

    private void backTrace(int[] source, int pre, Set<Integer> all, List<Integer> tmp) {
        if (tmp.size() == source.length) {
            int des = this.formNumber(tmp);
            all.add(des);
            return;
        }
        for (int j = pre; j < source.length; j++) {
            if (tmp.contains(source[j])) {
                continue;
            }
            tmp.add(source[j]);
            backTrace(source, pre, all, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    private int formNumber(List<Integer> tmp) {
        int res = 0;
        for (Integer integer : tmp) {
            res = res * 10 + integer;
        }
        return res;
    }

    @Test
    public void testgetPermu() {
        String res = getPermutation(4, 9);
        System.out.println(res);
    }

    public boolean detectCapitalUse(String word) {
        //首字母大写,全大写,全小写,只有这三种合法
        if (word == null || word == "") {
            return true;
        }
        if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
            int count = 1;
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                    count++;
                }
            }
            return count == word.length() || count == 1;
        } else {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testCapital() {
        System.out.println(detectCapitalUse("FlaG"));
    }


    public int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                count++;
                set.remove(s.charAt(i));
            }
        }
        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }

    @Test
    public void testLongestPalidrome() {
        System.out.println(longestPalindrome("abccccdd"));
    }

    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    public static void main(String[] argv) {
        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

    //leetcode290
    public boolean wordPattern(String pattern, String str) {
        char[] chars = pattern.toCharArray();
        String[] strings = str.split(" ");
        if (chars.length != strings.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> values = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) != null) {
                if (map.get(chars[i]).equals(strings[i])) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if (values.contains(strings[i])) {
                    return false;
                }
                map.put(chars[i], strings[i]);
                values.add(strings[i]);
            }
        }
        return true;
    }

    public String decodeString(String s) {
        if (s == null) {
            return s;
        }
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                //count数量是有可能大于10的
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                //清空res
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public int calculate(String s) {
        //实现一个计算器
        if (s == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char preOperator = '+';
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
            } else if (i == s.length() || s.charAt(i) != ' '){
                if (preOperator == '+') {
                    stack.push(num);
                } else if (preOperator == '-') {
                    stack.push(-num);
                } else if (preOperator == '*') {
                    stack.push(stack.pop() * num);
                } else if (preOperator == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                preOperator = s.charAt(i);
            }
        }
        int res = 0;
        for (Integer integer : stack) {
            res += integer;
        }
        return res;
    }

        //旋转数组的二分查找
    public boolean search(int[] nums, int target) {
        if (nums == null) {
            return false;
        }
        int left = 0;
        int right = nums.length;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[0]) {
                if (target >= nums[0] && target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[0]) {
                if (target >= nums[mid] && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                left++;
            }
        }
        return false;
    }
}
