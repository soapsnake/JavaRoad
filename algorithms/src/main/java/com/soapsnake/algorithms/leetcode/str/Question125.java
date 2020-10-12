package com.soapsnake.algorithms.leetcode.str;

public class Question125 {

    //菜逼版
    public boolean isPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        char leftPoint;
        char rightPoint;
        while (right >= left) {
            if (Character.isLetterOrDigit(s.charAt(left))) {
                leftPoint = s.charAt(left);
            } else {
                left++;
                continue;
            }
            if (Character.isLetterOrDigit(s.charAt(right))) {
                rightPoint = s.charAt(right);
            } else {
                right--;
                continue;
            }
            if (Character.toUpperCase(leftPoint) != Character.toUpperCase(rightPoint)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //大神三行搞定版
    public boolean isPalindrome2(String s) {
        //删除不合法字符 && 全部转成小写
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        //字符串翻转
        String rev = new StringBuffer(actual).reverse().toString();
        //翻转字符串确定与源字符串是否相等
        return actual.equals(rev);
    }

    public static void main(String[] args) {
        Question125 question125 = new Question125();
        String s = "race a car";
        System.out.println(question125.isPalindrome(s));
    }
}
