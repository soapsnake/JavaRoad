package com.ld.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/18
 */
public class Question258 {

    public int addDigits(int num) {
        String numStr = num + "";
        while (numStr.length() != 1) {
            char[] chars = numStr.toCharArray();
            int temp = 0;
            for (int i = 0; i < chars.length; i++) {
                int curr = Integer.valueOf(chars[i] + "");
                temp += curr;
            }
            numStr = temp + "";
        }
        return Integer.valueOf(numStr);
    }

    //todo 更巧妙的解法:取余
    /**
     * Say a number x = 23456
     * x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     * 2 * 10000 % 9 = 2 % 9
     * 3 * 1000 % 9 = 3 % 9
     * 4 * 100 % 9 = 4 % 9
     * 5 * 10 % 9 = 5 % 9
     * Then x % 9 = ( 2+ 3 + 4 + 5 + 6) % 9, note that x = 2* 10000 + 3 * 1000 + 4 * 100 + 5 * 10 + 6
     * So we have 23456 % 9 = (2 + 3 + 4 + 5 + 6) % 9
     */

    public static void main(String[] args) {
        Question258 question258 = new Question258();
        System.out.println(question258.addDigits(38));
        for (int i = 0; i <= 19 ; i++) {
            System.out.println( "i = "+i + " and i % 9 = " + i % 9);
        }
    }
}
