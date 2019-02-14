package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-14 09:56
 */
public class Question680 {
    /**
     * 字符串是否能最多删除一个字符变成回文串
     */
    public boolean validPalindrome(String s) {

        //左右指针碰撞
        int left = 0;
        int right = s.length() - 1;
        return recursion(s, left, right, 0);
    }

    private boolean recursion(String s, int left, int right, int invalid) {
        if (left >= right) {  //能够正常递归结束,说明字串没有问题
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            invalid++;
            if (invalid > 1) {
                return false;
            }
            return recursion(s, left, right - 1, invalid) || recursion(s, left + 1, right, invalid);
        } else {
            return recursion(s, ++left, --right, invalid);
        }
    }

    public static void main(String[] args) {
        Question680 question680 = new Question680();
        String s = "abc";
        System.out.println(question680.validPalindrome(s)); //should be true
    }
}
