package com.soapsnake.algorithms.leetcode.str;

import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question168 {

    public static void main(String[] args) {
        Question168 question168 = new Question168();
        System.out.println(question168.convertToTitle(52));

        System.out.println(strAdd("823", "597"));
    }

    public static String strAdd(String a, String b) {
        int len = Math.min(a.length(), b.length());
        StringBuilder stringBuffer = new StringBuilder();
        int step = 0; //进位
        for (int i = len - 1; i >= 0; i--) {
            int c = 0;  //位实际值
            int numA = a.charAt(i) - '0';
            int numB = b.charAt(i) - '0';
            c = (numA + numB + step) % 10;        //余10得本位值,注意进位
            step = (numA + numB + step) / 10;     //除10得进位
            stringBuffer.insert(0, c + "");
        }
        if (step != 0) {
            stringBuffer.insert(0, step + "");
        }
        return stringBuffer.toString();
    }

    /**
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     * ...
     * <p>
     * Example 1:
     * Input: 1
     * Output: "A"
     * <p>
     * Example 2:
     * Input: 28
     * Output: "AB"
     * <p>
     * Example 3:
     * Input: 701
     * Output: "ZY"
     */
    public String convertToTitle(int n) {
        //思路:26进制,除以26进1,  27 / 26 = 1 , 27 % 26 = 1
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }


    public long kMirror(int k, int n) {
        long res = 0;
        List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>();

        list1.add("");
        list2.add("0");
        for (int i = 1; i < k && n > 0; i++){
            list2.add(Integer.toString(i));
            res += i;
            n--;
        }

        return res + constructMirrorNumbers(2, k, n, list1, list2);
    }

    long constructMirrorNumbers(int len, int k, int n, List<String> list1, List<String> list2) {
        if (n == 0)
            return 0;

        long res = 0;
        List<String> cur = new ArrayList<>();
        for (int i = 0; i < k && n > 0; i++) {
            List<String> list = len % 2 == 0 ? list1 : list2;
            for (int j = 0; j < list.size() && n > 0 ; j++) {
                String s = i + list.get(j) + i;
                cur.add(s);
                long num = Long.parseLong(s, k);
                if (i != 0 && isPalind(Long.toString(num))) {
                    n--;
                    res += num;
                }
            }
        }

        if (len % 2 == 0) {
            list1 = cur;
        } else {
            list2 = cur;
        }
        return res + constructMirrorNumbers(len+1, k, n, list1, list2);
    }

    boolean isPalind(String s) {
        int len = s.length();
        int middle = len / 2 - 1;
        for (int i = 0; i <= middle; i++) {
            if (s.charAt(i) != s.charAt(len-1-i)) {
                return false;
            }
        }
        return true;
    }


    String[] step = {"null","A", "B","C","D","E","F"};
    public String change(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            System.out.println("---" + n  % k);

            sb.insert(0, step[n % k]);
            n /= k;
        }
        sb.insert(0, "0x");
        return sb.toString();
    }

    @Test
    public void testChange() {
        System.out.println(Integer.toHexString(200));
        System.out.println(change(20, 16));
    }

    public static int binerSearch(long[] arr, long k) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            //if (arr[mid] == k) {
            //    return mid + 1;
            //}else if (arr[mid] > k) {
            //    r = mid - 1;
            //}else {
            //    r = mid + 1;
            //}

             if (arr[mid] == k) {
                     return mid + 1;
                 } else if (arr[mid] > k)  {
                     r = mid - 1;
                 } else {
                     l = mid + 1;
                 }

        }
        return l;
    }


}
