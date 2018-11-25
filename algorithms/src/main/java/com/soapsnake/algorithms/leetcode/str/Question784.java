package com.soapsnake.algorithms.leetcode.str;

import com.soapsnake.algorithms.leetcode.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author soapsnake
 * @date 2018/11/17
 */
class Question784 {

    //todo 这个题目没有那么简单,其实是树的遍历问题
    public List<String> letterCasePermutation(String S) {
        int smallLeft = 'a';
        int smallRight = 'z';
        int bigLeft = 'A';
        int bigRight = 'Z';
        int range = 'a' - 'A';

        char[] chars = S.toCharArray();

        Set<String> strings = new HashSet<>();
        strings.add(S);
        for (int i = 0; i < chars.length; i++) {
            char[] tempChars = Arrays.copyOfRange(chars, 0, chars.length);
            int curr = (int) chars[i];
            if (smallLeft <= curr && curr <= smallRight) {
                tempChars[i] = (char) (tempChars[i] - range);
                String temp = new String(tempChars);
                strings.add(temp);
            }
            if (bigLeft <= curr && curr <= bigRight) {
                tempChars[i] = (char) (tempChars[i] + range);
                String temp = new String(tempChars);
                strings.add(temp);
            }
        }
      return new ArrayList<>(strings);
    }

    public static void main(String[] args) {
        Question784 question784 = new Question784();
        ArrayUtils.printList(question784.letterCasePermutation("a1b2"));

    }
}
