package com.soapsnake.algorithms.leetcode.biner;

/**
 *
 * Created on 2020-04-23
 */
public class Question201 {

    //leetcode201
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
}
