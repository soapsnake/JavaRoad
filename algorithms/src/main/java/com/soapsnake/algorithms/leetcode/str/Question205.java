package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-03 20:56
 */
public class Question205 {

    //还是没有看懂
    public boolean isIsomorphic(String s, String t) {
        if(s == null || s.length() <= 1) return true;
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0 ; i< s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)){
                if(map.get(a).equals(b))
                    continue;
                else
                    return false;
            }else{
                if(!map.containsValue(b))
                    map.put(a,b);
                else return false;

            }
        }
        return true;
    }

    //没有搞懂
    public boolean isIsomorphic2(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {   //char字符会转换成ASCii值
                return false;
            }
            m1[s.charAt(i)] = i + 1;  //a -> 1   b -> 1
            m2[t.charAt(i)] = i + 1; //b -> 1    a -> 1
        }
        return true;
    }



    public static void main(String[] args) {
        Question205 question205 = new Question205();
        String s = "egg";
        String t = "add";
        System.out.println(question205.isIsomorphic(s, t));
    }
}
