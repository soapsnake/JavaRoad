package com.ld.leetcode.str;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 */
class Question461 {

    public static void main(String[] args) {
        Question461 question461 = new Question461();
        int res = question461.hammingDistance(1, 4);
        System.out.println(res);
    }

    //思路:java二进制的异或操作,是对应位如果不相同,最终运算结果为1,如果相同,运算结果为0,因此x^y,统计所得数字的二进制中1的个数即可
    //编写提交一遍过,这把666
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        String res = Integer.toBinaryString(z);

        char[] chars = res.toCharArray();
        int total = 0;
        for (Character c : chars) {
            if (c.equals('1')) total += 1;
        }
        return total;
    }

    //解法2:一行代码解决问题!!!!!!!!!!!
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
