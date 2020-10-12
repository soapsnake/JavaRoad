package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 00:14
 */
public class Question290 {

    //todo
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (arr.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(pattern.charAt(i))) {  //如果a -> b,也就是pattern没有出现重复
                if (!set.add(arr[i])) {   //但是字符串却重复了
                    return false;
                }
                map.put(pattern.charAt(i), arr[i]);  //字符串也没重复,那么放进map
            } else if (!map.get(pattern.charAt(i)).equals(arr[i])) {  //如果a -> a,patten重复了,但是map拿出来的字符串不重复
                return false;
            }
        }
        return true;
    }
}
