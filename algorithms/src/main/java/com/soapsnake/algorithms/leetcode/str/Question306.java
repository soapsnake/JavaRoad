package com.soapsnake.algorithms.leetcode.str;

public class Question306 {

    public static void main(String[] args) {
        Question306 question306 = new Question306();
        String num = "1123581";
//        System.out.println(question306.isAdditiveNumber(num));

        System.out.println(num.startsWith("124", 1));
    }

    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i)
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j)  //i和j的长度都是可变的,先变j也就是第二段的长度
                if (isValid(i, j, num)) return true;
        return false;
    }

    private boolean isValid(int i, int j, String num) {  //i代表第一段数字的长度, j代表第二段数字的长度
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));   //[0, i)
        Long x2 = Long.parseLong(num.substring(i, i + j));  //[i , i + j)
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }

    public boolean isAdditiveNumber2(String num) {
        if (num == null || num == " ") {
            return false;
        }
        if (num.length() < 3) {
            return false;
        }
        int prePre = num.charAt(0) - '0';
        int pre = num.charAt(1) - '0';
        for (int i = 2; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            if (cur != (pre + prePre)) {
                return false;
            }
            prePre = pre;
            pre = cur;
        }
        return true;
    }
}
