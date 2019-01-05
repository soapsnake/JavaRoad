package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedList;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 00:21
 */
public class Question949 {

    public String largestTimeFromDigits(int[] A) {
        LinkedList<String> q = new LinkedList<>();
        q.add("");

        //把数组中的所有数字进行排列组合,记录下所有可能得组合方式
        for (int n : A) {
            for (int size = q.size(); size > 0; size--) {
                String s = q.poll();
                for (int i = 0; i <= s.length(); i++)
                    q.add(s.substring(0, i) + n + s.substring(i));
            }
        }
        String largest = "";
        for (String s : q) {
            s = s.substring(0, 2) + ":" + s.substring(2);

            //这个比较完全没有看懂?字符的compareto比较的是ASCII值吗?
            if (s.charAt(3) < '6' && s.compareTo("24:00") < 0 && s.compareTo(largest) > 0)
                largest = s;
        }
        return largest;
    }

    public static void main(String[] args) {
        Question949 question949 = new Question949();
        int[] a = {1,2,3,4};
        System.out.println(question949.largestTimeFromDigits(a));
    }
}
