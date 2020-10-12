package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-25 23:30
 */
public class Question859 {

    public static void main(String[] args) {
        Question859 question859 = new Question859();
        String a = "ab";
        String b = "ab";
        System.out.println(question859.buddyStrings(a, b));
    }

    //leetcode859
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {
            Set<Character> s = new HashSet<>();
            for (char c : A.toCharArray()) {
                s.add(c);
            }
            return s.size() < A.length();  //A和B是否有重复字符
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) != B.charAt(i)) {
                dif.add(i);
            }
        }
        //只有两个字符不同而且交换后相同
        return dif.size() == 2 && A.charAt(dif.get(0)) == B.charAt(dif.get(1)) && A.charAt(dif.get(1)) == B.charAt(dif.get(0));
    }
}
