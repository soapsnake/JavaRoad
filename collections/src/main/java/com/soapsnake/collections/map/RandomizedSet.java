package com.soapsnake.collections.map;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RandomizedSet {
    private Random ran;
    private List<Integer> list;   //index, val
    private Map<Integer, Integer> map;   // val, index
    private int index = 0;

    public RandomizedSet() {
        this.ran = new Random();
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(index, val);
        map.put(val, index);
        index++;
        return true;
    }

    //删除时,把list最后一个元素挪到删除的元素处,把空堵上
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int destIndex = map.get(val);
        map.remove(val);
        if (index - 1 == 0) return true;
        int lastVal = list.get(index - 1);
        list.add(destIndex, lastVal);
        map.put(lastVal, destIndex);
        index--;
        return true;
    }

    public int getRandom() {
        int dextIndex = ran.nextInt(index);
        return list.get(dextIndex);
    }

    public static void main(String[] args) {
//        RandomizedSet s = new RandomizedSet();
//        System.out.println(s.remove(0));
//        System.out.println(s.remove(0));
//        System.out.println(s.insert(0));
//        System.out.println(s.getRandom());
//        System.out.println("--->" + s.map);
//        System.out.println(s.remove(0));
//        System.out.println("===>" + s.map);
//        System.out.println(s.insert(0));



        String fdsf = "a";
        System.out.println(fdsf.substring(0, 1));
        StringBuilder sb = new StringBuilder();
        sb.reverse();
        StringBuffer sb2 = new StringBuffer();
        sb2.append("fds");
//        System.out.println(fibonacci(5));
    }


    public long countSubstrings(String s, char c) {
        //左指针往右移动,遇到c开始启动右指针,同时count+1
        //右指针往右移动,遇到c,count+1,一直到s的末尾
        char[] ch = s.toCharArray();
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == c) {
                int j = i;
                while (j < ch.length) {
                    if (ch[j] == c) count++;
                    j++;
                }
            }
        }
        return count;
    }


    public long countSubstrings2(String s, char c) {
        //左指针往右移动,遇到c开始启动右指针,同时count+1
        //右指针往右移动,遇到c,count+1,一直到s的末尾
        char[] ch = s.toCharArray();
        long t = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == c) {
                t++;
            }
        }
        return t * (t - 1);
    }


    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

