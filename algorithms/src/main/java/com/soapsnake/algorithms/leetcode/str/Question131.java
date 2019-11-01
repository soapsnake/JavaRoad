package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-23 23:45
 */
public class Question131 {

    public static void main(String[] args) {
        Question131 question131 = new Question131();
        String s = "aab";
        System.out.println(question131.partition(s));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        backTrace(s, res, new ArrayList<>());
        return res;
    }

    private void backTrace(String s, List<List<String>> res, List<String> tmp) {
        if (s == null || s.length() == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);   //主要这里用了subString而不是charAt,难倒了
            if (!this.ispalidram(str)) {
                continue;
            }
            tmp.add(str);
            backTrace(s.substring(i), res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean ispalidram(String str) {
        if (str.length() == 1) {
            return true;
        }
        int left = 0;
        int right = str.length() - 1;
        while (right >= left) {
            if (str.charAt(right) != str.charAt(left)) {
                return false;
            }
            right--;
            left++;
        }
        return true;
    }
}
