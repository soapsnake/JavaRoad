package com.soapsnake.algorithms.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/12
 */
class Question292 {
    public boolean canWinNim(int n) {
        /**
         *todo 只要是4的倍数你就不可能赢, 比如n = 8:
         * 第一种 :你拿了3, 你对手拿了1, 剩余4 (对手往他最能赢的方向去拿,不一定拿和你一样)
         * 第二种: 你拿2, 对手也拿2
         * 第三种: 你拿1,对手1......
         * 只要最后还剩4个,你就必输
         */
        return n % 4 != 0;
    }

    public static void main(String[] args) {
        Question292 question292 = new Question292();
        System.out.println(question292.canWinNim(4));
    }
}
