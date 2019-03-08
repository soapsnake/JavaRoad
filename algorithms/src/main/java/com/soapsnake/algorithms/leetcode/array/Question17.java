package com.soapsnake.algorithms.leetcode.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not MAP to any letters.
 */
class Question17 {

    public static final Map<String, List> MAP;
    static {
        Map<String, List<String>> map1 = new HashMap<>();
        map1.put("2", Arrays.asList("a", "b", "c"));
        map1.put("3", Arrays.asList("d", "e", "f"));
        map1.put("4", Arrays.asList("g", "h", "i"));
        map1.put("5", Arrays.asList("j", "k", "l"));
        map1.put("6", Arrays.asList("m", "n", "o"));
        map1.put("7", Arrays.asList("p", "q", "r", "s"));
        map1.put("8", Arrays.asList("t", "u", "v"));
        map1.put("9", Arrays.asList("w", "x", "y", "z"));
        MAP = Collections.unmodifiableMap(map1);
    }

    private List<List<String>> sources = new ArrayList<>();
    int count = 0;
    public List<String> letterCombinations2(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        count = digits.length();
        for (int i = 0; i < digits.length(); i++) {
            List<String> temp = MAP.get(digits.charAt(i) + "");
            sources.add(temp);
        }
        System.out.println("sources = " + sources);
        dfsTree(0, "");
        return result;
    }

    private List<String> result = new ArrayList<>(2);
    private void dfsTree(int cur, String pre) {
        if (cur >= count) {
            result.add(pre);
            return;
        }
        List<String> curList = sources.get(cur);
        for (int i = 0; i < curList.size(); i++) {
            String temp = curList.get(i);

            //这里如果写成pre += temp,然后下面传pre就不对了
            dfsTree(cur + 1, pre + temp);
        }
    }

    public static void main(String[] args) {

        String input = "23";
        Question17 question17 = new Question17();
        System.out.println(question17.letterCombinations2(input));
        //output = ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    }

}
