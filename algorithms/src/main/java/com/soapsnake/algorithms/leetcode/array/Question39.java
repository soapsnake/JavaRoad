package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-04 21:01
 */
public class Question39 {

    /**
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            //1. 能整除
            int left = 0;
            int right = candidates.length - 1;
            while (right > left) {
                while (left < right) {

                }



            }



            //2. 求和

           //3. 一个数的乘方与其他数求和


            return  res;
    }
}
