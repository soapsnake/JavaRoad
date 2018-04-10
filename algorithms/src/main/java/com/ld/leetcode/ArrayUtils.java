package com.ld.leetcode;

import java.util.List;

public class ArrayUtils {

    public static void printArr(int[] arr){
        if(arr == null){
            System.out.println("数组为空");
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            sb.append(arr[i]);
            if(i != arr.length-1){
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }

    public static <T> void printList(List<T> list){
        if (list == null || list.isEmpty()){
            System.out.println("list 为空");
        }
        for (T t : list){
            System.out.println(t.toString());
        }
    }
}
