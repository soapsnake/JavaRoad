package com.soapsnake.algorithms.leetcode.biner;

/**
 * Given a positive integer, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 */
class Question476 {

    // 老实说这个解法根本就看不懂
    //leetcode476
    public int findComplement(int num) {
        return ~num + (Integer.highestOneBit(num) << 1);
    }


    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return -1;
        }
        int[] count = new int[26];
        for (int i = 0; i < chars.length; i++) {
            count[chars[i] - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Question476 question476 = new Question476();
        String s = "leetcode";
        System.out.println(question476.firstUniqChar(s));
    }

}
