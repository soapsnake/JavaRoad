package com.soapsnake.algorithms.leetcode.biner;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-05
 */
public class Question1009 {

        public int bitwiseComplement(int N) {
            if (N == 0) return 1;
            if (N == 1) return 0;
            int x = 1;
            while(x<= N){
                x = x << 1;  // equialently written as x*=2;
            }
            return N ^ (x-1);
        }
}
