package com.soapsnake.algorithms.leetcode.str;

/**
 * 
 * Created on 2021-05-16
 */
public class Question65 {

    //leetcode65
    public boolean isNumber(String s) {
        try {
            s = s.trim();
            int n = s.length();
            if (n == 0 || (s.charAt(n - 1) != '.' && (s.charAt(n - 1) - '0' < 0 || s.charAt(n - 1) - '0' > 9))) {
                return false;
            }
            double i = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
