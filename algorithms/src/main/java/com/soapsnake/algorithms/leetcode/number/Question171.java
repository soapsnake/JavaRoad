package com.soapsnake.algorithms.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/24
 */
public class Question171 {

    public static void main(String[] args) {
        Question171 question171 = new Question171();
        System.out.println(question171.titleToNumber("ZY"));

        System.out.println((int) Math.pow(10, 0));

        System.out.println("Z - A = " + ('Z' - 'A' + 1));
    }

    /**
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Y -> 25
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     * <p>
     * Input: "ZY"
     * Output: 701
     *
     * @param s
     * @return
     */
    //leetcode171
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            res += (s.charAt(i) - 'A' + 1) * (int) Math.pow(26, chars.length - i - 1);
        }
        return res;
    }

}
