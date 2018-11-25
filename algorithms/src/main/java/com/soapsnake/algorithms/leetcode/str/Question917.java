package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/11
 */
class Question917 {

    public String reverseOnlyLetters(String S) {
        /**
         * todo 解法是错的,题目要求除了23个英语字母外其他符号都不能动,这里只处理了'-'
         * 那么这个解法其实也只能处理一种变异符号,一种以上就没办法了,还是得用双指针
         */

        char[] chars = S.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> index = new ArrayList<>();
        int i = 0;
        for (char c : chars) {
            if (c == '-') {
                index.add(i);
            } else {
                stringBuilder.insert(0, c + "");
            }
            i++;
        }
            for (Integer integer : index) {
                stringBuilder.insert(integer, "-");
            }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "a-bC-dEf-ghIj"; // j-Ih-gfE-Cb-a
        Question917 question917 = new Question917();
        System.out.println(question917.reverseOnlyLetters(s));
    }
}
