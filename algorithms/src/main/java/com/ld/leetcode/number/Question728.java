package com.ld.leetcode.number;

import com.ld.leetcode.list.ArrayUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A self-dividing number is a number that is divisible by every digit it contains.
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * Also, a self-dividing number is not allowed to contain the digit zero.
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 */

public class Question728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i=left;i<= right; i++){
            String temp = i + "";
            if (temp.contains("0")){
                continue;
            }
            int sort = 0;
            for (char c: temp.toCharArray()){
                if (i % Integer.valueOf(c + "") != 0){
                    break;
                }else {
                    sort++; //符合
                }
            }
            if (sort == temp.toCharArray().length){
                res.add(i);
            }
        }
        return res;
    }

    //推荐版解法
    public List<Integer> selfDividingNumbers2(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left, n = 0; i <= right; i++) {
            for (n = i; n > 0; n /= 10)
                if (n % 10 == 0 || i % (n % 10) != 0) break;
            if (n == 0) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int left = 1;
        int right = 22;

        Question728 question728 = new Question728();
        List<Integer> res = question728.selfDividingNumbers(left, right);
        ArrayUtils.printList(res);

        System.out.println(128 % 1 == 0);

        System.out.println(128 % 2 == 0);

        System.out.println(128 % 8 == 0);

        System.out.println(1 % 1 == 0);

        System.out.println(1 % 1 != 0);

        System.out.println(289 % 100);
    }

}
