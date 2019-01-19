package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 21:40
 */
public class Question409 {

    //傻逼版解法
    public int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0 ;i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
        int len = 0;
        int maxOdd = 0;
        List<Integer> oddNumber = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                len += entry.getValue();
            } else {
                oddNumber.add(entry.getValue());
            }
        }
        oddNumber.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int end = oddNumber.size() - 1;

        //如果是最大奇数字符的话全部保留,不是的话取n-1个字符(必然是偶数),这个要排序啊
        for (int i = 0; i <= end; i++) {
            if (i == end) {
                len += oddNumber.get(i);
                break;
            }
            len += oddNumber.get(i) - 1;
        }
        return len;
    }

    public static void main(String[] args) {
        Question409 question409 = new Question409();
        System.out.println(question409.longestPalindrome("abccccdd"));
    }

    //大神版解法
    public int longestPalindrome2(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
    }
}
