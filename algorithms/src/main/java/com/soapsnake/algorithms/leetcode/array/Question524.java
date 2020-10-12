package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2020-04-06
 */
public class Question524 {
    //leetcode524
    public String findLongestWord(String s, List<String> d) {
        String longest = "";
        for (String dictWord : d) {
            int i = 0;
            for (char c : s.toCharArray())
                //如果i小于字典词的长度并且 s的当前字符与字典当前字符相同那么移动i指针,i指针其实保存了相同字符的长度
                if (i < dictWord.length() && c == dictWord.charAt(i)) i++;

            if (i == dictWord.length() && dictWord.length() >= longest.length())
                if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
                    longest = dictWord;
        }
        return longest;
    }

    public int countElements(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int res = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key + 1) != null) {
                res += map.get(key);
            }
        }
        return res;
    }
}
