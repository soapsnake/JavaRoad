package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-25 23:30
 */
public class Question859 {

    //整体时间复杂度为2xO(n),算是非常高效的算法了!!!!
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        int left = 0;
        int right = A.length() - 1;
        char[] charA = A.toCharArray();
        char[] charB = B.toCharArray();

        //如果A和B完全相同,那么只要A中有重复字符串就绝对可以
        //字符串equals时间复杂度O(n)
        if (A.equals(B)) {
            return this.hasDup(charA);
        }

        //如果A和B不相同,那么只需要利用双指针交换不同的字符然后再比较即可
        //时间复杂度O(n)
        while (left < right) {
            while (left < right) {
                if (charA[left] != charB[left]) {
                    break;
                }
                left++;
            }

            while (right > left) {
                if (charA[right] != charB[right]) {
                    break;
                }
                right--;
            }

            if (left < right) { //这里一定要加 left < right!!!!
                char temp = charA[left];
                charA[left] = charA[right];
                charA[right] = temp;
                if (Arrays.equals(charA, charB)) {
                    return true;
                }
                left++;
                right--;
            }
        }
        return false;
    }

    private boolean hasDup(char[] charA) {
        Set<Character> set = new HashSet<>();
        for (char c : charA) {
            if (!set.add(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Question859 question859 = new Question859();
        String a = "ab";
        String b = "ab";
        System.out.println(question859.buddyStrings(a, b));
    }
}
