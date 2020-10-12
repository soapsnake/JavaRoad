package com.soapsnake.algorithms.leetcode.str;

import java.util.Stack;

public class Question394 {

    public static void main(String[] args) {
        Question394 question394 = new Question394();
        String s = "2[abc]3[cd]ef";

        //abcabccdcdcdef
        System.out.println(question394.decodeString(s));
    }

    /**
     * Examples:
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     *
     * @param s
     * @return
     */
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
}
