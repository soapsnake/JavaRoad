package com.ld.leetcode.str;

public class Question3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s .equals("")) return 0;
        if (s.equals(" ")) return 1;
        char[] chars = s.toCharArray();
        int max = - Integer.MAX_VALUE;
            String temp = "";
            for (int i=0;i<chars.length;i++){
                temp += chars[i];
                for (int j=i+1;j<chars.length;j++){
                    if (temp.contains(chars[j] + "")){
                        break;
                    }else{
                        temp += chars[j];
                    }
                }
                max = Math.max(temp.length(), max);
                temp = "";
            }
        return max;
    }

    public static void main(String[] args) {
        Question3 question3 = new Question3();
        String str = "abcabcbb";
        String str2 = "pwwkew";
        System.out.println(question3.lengthOfLongestSubstring(str));
    }
}
