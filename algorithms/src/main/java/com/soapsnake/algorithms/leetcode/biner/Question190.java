package com.soapsnake.algorithms.leetcode.biner;

public class Question190 {

    /**
     * 把整形数字转换成二进制串,然后翻转,输出新的二进制串表示的数字
     * Example 1:
     * Input: 00000010100101000001111010011100
     * Output: 00111001011110000010100101000000
     *
     * Example 2:
     * Input: 11111111111111111111111111111101
     * Output: 10111111111111111111111111111111
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0;i<32;i++){
            int end = n & 1;
            n >>= 1;    // n /= 2
            result <<=1;   //result *= 2
            result |= end; //???
        }
        return result;
    }
}
