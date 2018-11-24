package com.ld.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/24
 */
public class Question171 {

    /**
     *  A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Y -> 25
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     *
     *     Input: "ZY"
     * Output: 701
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        //A -> 1
        //AB -> 28 = 1 * 26 + 2

        //ZY -> 701 = 26 * (26) + 25

        //BBA -> 2 * (26)² + 2 * (26) + 1 = .....
        //实际上是26进制
        //这个map实际上根本就不需要
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < 26; i++) {
//            map.put((char)('A' + i), i+1);
//        }

        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            res += (s.charAt(i) - 'A' + 1) * (int)Math.pow(26, chars.length - i - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Question171 question171 = new Question171();
        System.out.println(question171.titleToNumber("ZY"));

        System.out.println((int) Math.pow(10, 0));

        System.out.println("Z - A = " + ('Z' - 'A' + 1) );
    }

}
