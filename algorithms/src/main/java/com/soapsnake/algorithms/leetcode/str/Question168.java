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

        System.out.println(strAdd("823", "597"));
    }


    public  static String strAdd(String a , String b) {
        int len = Math.min(a.length(), b.length());
        StringBuilder stringBuffer = new StringBuilder();
        int step = 0; //进位
        for (int i = len - 1;i >= 0; i--) {
            int c = 0;  //位实际值
            int numA = a.charAt(i) - '0';
            int numB = b.charAt(i) - '0';
            c = (numA + numB + step) % 10;        //余10得本位值,注意进位
            step = (numA + numB + step) / 10;     //除10得进位
            stringBuffer.insert(0, c + "");
        }
        if (step != 0) {
            stringBuffer.insert(0, step + "");
        }
        return stringBuffer.toString();
    }


}
