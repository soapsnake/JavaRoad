package com.soapsnake.algorithms.leetcode.str;

public class Question168 {

    /**
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     *
     * Example 1:
     * Input: 1
     * Output: "A"
     *
     * Example 2:
     * Input: 28
     * Output: "AB"
     *
     * Example 3:
     * Input: 701
     * Output: "ZY"
     */
    public String convertToTitle(int n) {
        //思路:26进制,除以26进1,  27 / 26 = 1 , 27 % 26 = 1
        StringBuilder result = new StringBuilder();
        while(n>0){
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Question168 question168 = new Question168();
        System.out.println(question168.convertToTitle(52));
    }
}
