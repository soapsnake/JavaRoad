package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-08 12:13
 */
public class Question12 {

    /**
     * Example 1:
     * Input: 3
     * Output: "III"
     * Example 2:
     * <p>
     * Input: 4
     * Output: "IV"
     * Example 3:
     * <p>
     * Input: 9
     * Output: "IX"
     * Example 4:
     * <p>
     * Input: 58
     * Output: "LVIII"
     * Explanation: L = 50, V = 5, III = 3.
     * Example 5:
     * <p>
     * Input: 1994
     * Output: "MCMXCIV"
     * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     */
    public String intToRoman(int num) {
        //todo 这道题做起来有点蛋疼
        /**
         * I             1
         * V             5
         * X             10
         * L             50
         * C             100
         * D             500
         * M             1000
         */

        int length = (num + "").length();

        int curNum = 0;
        int curWei = 0;
        String curStr = "";
        String res = "";

        for (int i = length - 1; i >= 0; i--) {
            curNum = num % (int) Math.pow(10, i);
            if (curNum >= 5) {
                if (i == 0) {

                } else if (i == 1) {

                } else if (i == 2) {

                } else {

                }
            } else {
                if (i == 0) {

                } else if (i == 1) {

                } else if (i == 2) {

                } else {

                }
            }
            res += curStr;
        }

        return res;
    }
}
