package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question187 {

    public static void main(String[] args) {

    }

    /**
     * Example:
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
     */
    //leetcode187
    public List<String> findRepeatedDnaSequences(String s) {
        //思路:暴力算,每次截取i -> i + 10的子串,看缓存里面是否已经存在,存在说明就重复了
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String temp = s.substring(i, i + 10);
            if (!set1.add(temp)) {   //set1已经添加过该子串,重复了
                set2.add(temp);   //这里的set2防止重复子串本身的重复
            }
        }
        return new ArrayList<>(set2);
    }

}
