package com.soapsnake.algorithms.leetcode.str;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author soapsnake
 * @date 2018/11/11
 */
class Question917 {

    /**
     * Input: "a-bC-dEf-ghIj"
     * Output: "j-Ih-gfE-dCba"
     */
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        /**
         * 这里为什么要使用treeMap:如果用hashmap,等会儿插入这个符号的时候没办法按照
         * 索引从小到大顺序插入(hashmap的key无顺序),就会造成arrayindexoutofbound
         */
        Map<Integer, Character> index = new TreeMap<>();
        int i = 0;
        for (char c : chars) {
            if (!this.isCharactor(c)) { //c是特殊字符
                index.put(i, c);
            } else {
                stringBuilder.insert(0, c + "");
            }
            i++;
        }

        for (Map.Entry<Integer, Character> entry : index.entrySet()) {
            stringBuilder.insert(entry.getKey(), entry.getValue() + "");
        }
        return stringBuilder.toString();
    }

    private boolean isCharactor(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }

        return c >= 'A' && c <= 'Z';
    }

    public static void main(String[] args) {
        String s = "a-bC-dEf-ghIj";
                 //"j-Ih-gfE-dCba"
        Question917 question917 = new Question917();
        System.out.println(question917.reverseOnlyLetters2(s));
    }

    //类似快排的左右指针碰撞,只要左右字符不是特殊字符就交换
    public String reverseOnlyLetters2(String S) {
        char[] chars = S.toCharArray();
        int left = 0;
        int right = S.length() - 1;
        while (right > left) {
            while (left < right) {
                if (Character.isLetter(chars[left])) {
                    break;
                }
                left++;
            }

            while (right > left) {
                if (Character.isLetter(chars[right])) {
                    break;
                }
                right--;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;   //容易遗漏这里
            right--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
