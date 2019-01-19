package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 20:55
 */
public class Question443 {

    //你能看懂这个算法的逻辑我就拜你为师!!
    public int compress(char[] chars) {
        if (chars == null) {
            return 0;
        }

        for (int i = 0;i < chars.length;) {
            int dup = 0;
            int firstIndex = 0;
            int j = i + 1;
            for (; j < chars.length; j++) {
                if (j == chars.length - 1) {
                    if (chars[j] == chars[i]) {
                        for (int start = firstIndex; start <= j; start++) {
                            chars[start] = ' ';
                        }
                        return chars.length;
                    }else {
                        if (dup == 0) {
                            return chars.length;
                        } else {
                            for (int start = firstIndex; start < j; start++) {
                                chars[start] = ' ';
                            }
                            return chars.length;
                        }
                    }
                }
                if (chars[j] != chars[i]) { //
                    if (dup == 0) {
                        //没有重复的
                        i++;
                        continue;
                    } else {
                        //出现过重复,但是现在重复结束了,endIndex
                        // 1.之前从第2次重复开始要改数字
                        //2. i指针现在要移到新的位置
                        int range = j - firstIndex;
                        for (int start = firstIndex; start < j; start++) {
                            chars[start] = ' ';
                        }
                        i = j;
                        break;
                    }
                }else {
                    if (dup == 0) { //第一次重复
                        firstIndex = j; // 首次重复索引
                        dup++;
                    } else {
                        dup++; //
                    }
                }
            }
        }
        return chars.length;
    }

    public static void main(String[] args) {
        Question443 question443 = new Question443();
        char[] chars = {'a','a','b','b','c','c','c'};
        System.out.println(question443.compress(chars));
    }
}
