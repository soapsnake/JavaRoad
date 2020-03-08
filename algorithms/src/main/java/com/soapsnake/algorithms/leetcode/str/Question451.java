package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-07
 */
public class Question451 {

    /**
     * Example 2:
     * Input:
     * "cccaaa"
     * Output:
     * "cccaaa"
     * Explanation:
     * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     * <p>
     * <p>
     * Example 3:
     * Input:
     * "Aabb"
     * Output:
     * "bbAa"
     * Explanation:
     * "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     */
    //leetcode451
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();  //char -> count
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Character>[] bucket = new List[s.length() + 1];  //数组的每个元素为一个list,类似hashmap
        for (char key : map.keySet()) {
            int frequency = map.get(key);  //count
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            //数组count索引处对应的是一个list,list中存放了出现该count次数的字符,比如aabb,buket[2]的list值就是[a,b]
            //这个操作其实是一个归并操作,把所有出现次数相同的char归到了同一个list中,index代表了list中各char的重复次数
            bucket[frequency].add(key);
        }

        StringBuilder sb = new StringBuilder();
        //倒着排,从多到少
        for (int pos = bucket.length - 1; pos >= 0; pos--)
            //不为null就表示该count索引位置有字符出现过
            if (bucket[pos] != null)
                for (char c : bucket[pos])
                    //map.get(c) = 字符c出现的次数
                    for (int i = 0; i < map.get(c); i++)
                        sb.append(c);

        return sb.toString();
    }


        public String frequencySort2(String s) {
        //傻逼解法,对map的value进行排序,由于leetcode识别不了entry类,所以编译失败
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(((o1, o2) -> o2 - o1)));
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                stringBuilder.append(entry.getKey());
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Question451 question451 = new Question451();
        String s = "Aabb";
        System.out.println(question451.frequencySort(s));
    }
}
