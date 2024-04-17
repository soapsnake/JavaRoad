package com.soapsnake.lab.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

    public final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public String name;
    public Integer age;
    public Integer gender;

    public String getSmallestString(String s, int k) {
        //每一个字符可以+1,也可以-1, a-1=z, z+1=a,
        //贪心: 从前往后遍历每一个字符,计算每一个字符变成a的距离,用k减去,如果k <=0,停止

        char[] tmp =new char[52];
        for(int i = 0; i < tmp.length; i++) {
            tmp[i] = (char) ('a' + i % 26);
        }
        System.out.println("tmp[0] = " +tmp[0] + " tmp[26]=" + tmp[26]);
        char[] ch = s.toCharArray();
        int n =ch.length;
        char[] res = new char[s.length()];
        for(int i = 0; i < n; i++) {
            int[] dis = distance(ch[i], tmp);
            System.out.println("dis = " + Arrays.toString(dis));
            if(dis[0] >= k) {
                res[i] = 'a';
                k -= dis[0];
            } else {
                if(dis[1] > 0) {
                    res[i] = (char)(ch[i] + dis[0]);
                } else {
                    res[i] = (char) (ch[i] + 26 + dis[0]);
                }
            }
        }
        List<Integer> fdisf  = new ArrayList<>();
        String sdfds = "";
        StringBuilder sb = new StringBuilder();
        char[] kjdsia = sb.reverse().toString().toCharArray();
        return new String(res);
    }

    private int[] distance(char a, char[] tmp) {
        int[] res = new int[2];
        System.out.println("tmp[a - 'a'] =" + tmp[a - 'a']);
        int i = tmp[a - 'a'];
        int j = tmp[a - 'a'] - 26;

        System.out.println("i = " + i + " j = " + j);

        if(Math.abs(i) < Math.abs(j)) {
            res[0] = Math.abs(i);
            res[1] = 1;
        } else {
            res[0] = Math.abs(j);
            res[1] = -1;
        }
        return res;
    }


    public static void main(String[] args) {

        Person p = new Person();
        String s= "zbbz";
        int k =3;
        System.out.println(p.getSmallestString(s, k));

    }

    public int longestMonotonicSubarray(int[] nums) {
        if(nums.length == 0) return 0;
        int n= nums.length;
        int jian = 1;
        int zen = 1;
        int maxZen = 1;
        int maxJian = 1;
        for(int i = 1; i < n; i++) {
            if(nums[i] > nums[i - 1]) {
                ++zen;
                maxZen = Math.max(zen, maxZen);
                jian = 1;
            }else if(nums[i] < nums[i - 1]) {
                jian++;
                maxJian = Math.max(jian, maxJian);
                zen = 1;
            } else {
                jian = 1;
                zen = 1;
            }
        }
        return Math.max(maxZen, maxJian);
    }




}
