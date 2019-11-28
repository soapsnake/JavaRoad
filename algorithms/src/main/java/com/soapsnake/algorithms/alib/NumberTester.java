package com.soapsnake.algorithms.alib;

import org.junit.Test;

public class NumberTester {

    int maxLen = 0;
    int left = 0;
    int right = 0;
    int mid = 1, fin = 0;

    public static int revertNum(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
//			if ((newResult - tail) / 10 != result) {
//				return 0;
//			}
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args) {
//		System.out.println(strToInt("1983498290483921043"));

//		System.out.println(revertNum(1));

        NumberTester utils = new NumberTester();
//		System.out.println(utils.findMaxSub("babad"));
        int[] arr = {0, 2, 2, 3, 5, 6, 7, 9, 10, 11, 13, 14, 18};
    }

    //求字符串“efabcbaefehiabcba”中最长的回文数，不去重（美团）
    public String findMaxSub(String string) {
        if (string == null || string.equals("")) {
            return string;
        }
        int len = string.length();
        for (int i = 0; i < len; i++) {
            extendStr(string, i, i);
            extendStr(string, i, i + 1);
        }
        return string.substring(left, right + 1);
    }

    private void extendStr(String string, int i, int j) {
        if (i < 0 || j >= string.length()) {
            return;
        }
        if (string.charAt(i) == string.charAt(j)) {
            if ((j - i) > maxLen) {
                maxLen = j - i;
                left = i;
                right = j;
            }
            i--;
            j++;
            extendStr(string, i, j);
        }
    }

    private void extendStrWhile(String string, int i, int j) {
        while (i > 0 && j < string.length() - 1 && string.charAt(i) == string.charAt(j)) {
            i--;
            j++;
        }
        if (j - i > maxLen) {
            maxLen = j - i;
            left = i;
            right = j;
        }
    }

    /**
     * 30.在从1到n的正数中1出现的次数
     * 题目：输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。
     * 例如输入12，从1到12这些整数中包含1 的数字有1，10，11和12，1一共出现了5次。
     * 分析：这是一道广为流传的google面试题。
     */
    public int count1(int n) {
        //思路:罗列所有数字,挨个遍历,单独写个方法判断数字中包含1的个数,有一定规律:
        //感觉有一定难度
        //1,
        //11, 21 ,31....
        //100, 101,  110,111, 120,121...130,131
        // 1 ~ 100: 1+=10,10,100 ~ 200
        int i = 1;
        int count = 0;
        while (i <= n) {
            if (i < 10) {
                count += 1;
            } else if (i < 20) {
                count += n - 10 + 2;
            } else if (i < 100) {
                count += 1;
            } else if (i < 120) {
                count += n - 110 + 2; //101,110,111
            } else if (i < 200) {
                count += 2;  // 121, 131, 141
            } else if (i < 220) {
                count += n - 210 + 2;
            }
            i += 10;
        }
        return count;
    }

    @Test
    public void testCount1() {
        System.out.println(count1(12));
    }
}
