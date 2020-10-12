package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-16 21:58
 */
public class Question345 {

    /**
     * 一遍过就是这么牛逼!!!!!!!!
     */
    private static List<Character> vows = Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U');

    public String reverseVowels(String s) {

        //左右指针碰撞算法
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (right > left) {

            while (right > left) {
                if (this.isvow(chars[left])) {
                    break;
                }
                left++;
            }

            while (right > left) {
                if (this.isvow(chars[right])) {
                    break;
                }
                right--;
            }

            if (right > left) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
            }
            right--;
            left++;
        }
        return new String(chars);
    }

    private boolean isvow(char aChar) {
        return vows.contains(aChar);
    }


    class RandomizedSet {

        private List<Integer> array;
        private Map<Integer, Integer> map;   // val -> list index
        Random random;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            array = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            Integer index = this.map.get(val);
            boolean res = false;
            if (index == null) {
                res = true;
                array.add(val);
                map.put(val, array.size() - 1);
                return res;
            } else {
                return false;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean res = false;
            Integer index= this.map.get(val);
            if (index != null){
                map.remove(val);
                array.remove(val);
                return true;
            } else {
                return false;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int index = random.nextInt(this.array.size());
            return array.get(index);
        }
    }
}
