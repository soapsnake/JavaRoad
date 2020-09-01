package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedList;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 00:21
 */
public class Question949 {

    public static void main(String[] args) {
        Question949 question949 = new Question949();
        int[] a = {1, 2, 3, 4};
        System.out.println(question949.largestTimeFromDigits(a));
    }

    public String largestTimeFromDigits(int[] A) {
        LinkedList<String> q = new LinkedList<>();
        q.add("");

        //把数组中的所有数字进行排列组合,记录下所有可能得组合方式
        for (int n : A) {
            for (int size = q.size(); size > 0; size--) {
                String s = q.poll();
                for (int i = 0; i <= s.length(); i++) {
                    String temp = s.substring(0, i) + n + s.substring(i);
                    System.out.println(temp);
                    q.add(temp);
                }
            }
        }
        String largest = "";
        for (String s : q) {
            s = s.substring(0, 2) + ":" + s.substring(2);

            //这个比较完全没有看懂?字符的compareto比较的是ASCII值吗?
            if (s.charAt(3) < '6' && s.compareTo("24:00") < 0 && s.compareTo(largest) > 0) {
                System.out.println("可能最大的时间; =>" + s);
                largest = s;
            }
        }
        return largest;
    }

    //backTracing 全排列
    public static String largestTimeFromDigits2(int[] A) {
        StringBuilder res = backtrack(new StringBuilder(), new StringBuilder(), A, new boolean[A.length]);
        if(res.length() == 4) {
            res.insert(2,":");
            return res.toString();
        }else {
            return res.toString();
        }
    }

    private static StringBuilder backtrack(StringBuilder res, StringBuilder temp, int[] A, boolean[] used){
        if(temp.length() == 4 && Integer.parseInt(temp.toString()) <= 2359
                && Integer.parseInt(temp.substring(2)) < 60){  //秒数小于60
            int tempTime = Integer.parseInt(temp.toString());
            return res.length() == 0 ? new StringBuilder(temp) : Integer.parseInt(res.toString()) > tempTime ? res : new StringBuilder(temp);
        }else{
            for(int i = 0; i < A.length; i++){
                if(used[i] || i > 0 && A[i] == A[i-1] && !used[i-1]) {
                    continue;
                }
                used[i] = true;
                temp.append(A[i]);
                res = backtrack(res, temp, A, used);
                used[i] = false;
                temp.deleteCharAt(temp.length() - 1);
            }
        }
        return res;
    }
}
