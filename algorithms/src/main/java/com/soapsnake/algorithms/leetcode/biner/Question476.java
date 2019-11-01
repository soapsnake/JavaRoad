package com.soapsnake.algorithms.leetcode.biner;

/**
 * Given a positive integer, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 */
class Question476 {

    public static void main(String[] args) {
        Question476 question476 = new Question476();
        System.out.println(question476.findComplement(5));
    }

    // 老实说这个解法根本就看不懂
    public int findComplement(int num) {
        return ~num + (Integer.highestOneBit(num) << 1);
    }

}
