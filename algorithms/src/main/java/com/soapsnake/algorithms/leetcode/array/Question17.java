package com.soapsnake.algorithms.leetcode.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not MAP to any letters.
 */
class Question17 {

    public static final Map<String, List> MAP;

    static {
        Map<String, List> map1 = new HashMap<>();
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

    public static void main(String[] args) {

        String input = "234";

        Question17 question17 = new Question17();
        System.out.println(question17.letterCombinations2(input));

        //output = ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null)
            return res;
        if (digits.equals("") || digits.equals(""))
            return res;
        if (digits.length() == 1) {
            return MAP.get(digits);
        }
        char[] chars = digits.toCharArray();
        //这个题目难在有多少个数字就会有多少层嵌套循环,有可能导致时间复杂度非常高
        //思路:把数字进行两两配对,然后计算每队的组合,最后汇总即可
        Set<List<String>> strings = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                strings.add(Arrays.asList(chars[i] + "", chars[j] + ""));
            }
        }
        for (List<String> str : strings) {
            List<String> tem = this.getListString(str);
            res.addAll(tem);
        }
        return res;
    }

    private List<String> getListString(List<String> list) {
        List<String> res = new ArrayList<>();
        String s1 = list.get(0);
        String s2 = list.get(1);
        List<String> list1 = MAP.get(s1);
        List<String> list2 = MAP.get(s2);
        for (String str : list1) {
            for (String str2 : list2) {
                res.add(str + str2);
            }
        }
        return res;
    }

    //正确的解法,真的是:我他妈无论如何都不可能想出来这样得解法!!!
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));    //char字符转int
            while (ans.peek().length() == i) {    //当栈顶元素得长度等于i时,这里为什么要这样搞了??????
                String t = ans.remove();      //弹出栈顶元素
                for (char s : mapping[x].toCharArray())    //分别取输入数字对应得三个英语字母
                    ans.add(t + s);            //栈顶部元素拼接
            }
        }
        return ans;
    }


}
