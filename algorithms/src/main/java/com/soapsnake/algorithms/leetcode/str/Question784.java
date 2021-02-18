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

    public static void main(String[] args) {
        Question784 question784 = new Question784();
        List<String> res = question784.letterCasePermutation("C");
        System.out.println(res.size());
        ArrayUtils.printList(res);
    }

    //这个题目太他妈有意思了!!!!!
    //leetcode784
    public List<String> letterCasePermutation(String S) {
        Set<String> res = new HashSet<>();
        if (S == null) {
            return new ArrayList<>(res);
        }
        if ("".equals(S)) {
            return Arrays.asList("");
        }
        char[] chars = S.toCharArray();
        //树的路径遍历算法
        this.treeDFS(chars, 0, res);
        return new ArrayList<>(res);
    }

    private void treeDFS(char[] chars, int index, Set<String> res) {
        if (index == chars.length) {
            res.add(String.valueOf(chars));
            return;
        }

        //如果是数字那么直接跳过不处理
        if (Character.isDigit(chars[index])) {
            treeDFS(chars, index + 1, res);
        }

        //如果是字符那么其实需要大转小,或者是小转大
        if (Character.isLetter(chars[index])) {
            //不变一次,相当于treeDFS(root.left, ...);
            treeDFS(chars, index + 1, res);

            //变相应大(小)写又一次,相当于treeDFS(root.right, ...);
            if (chars[index] - 'A' <= 25) {
                chars[index] = (char) (chars[index] - ('A' - 'a')); //大写转小写
            } else if (chars[index] - 'a' <= 25) {
                chars[index] = (char) (chars[index] + ('A' - 'a')); //小写转大写
            }
            treeDFS(chars, index + 1, res);
        }
    }
}
