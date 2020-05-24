package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created on 2020-04-18
 */
public class Question553 {

    //leetcode553
    //这道题的本质就是:分母应该尽可能的小,那么如何才能保证分母尽可能小了
    //很简单,让第二个数字开始一直往下除,因为都是正整数,所以分母会越来越小,最终结果就越来越大
    public String optimalDivision(int[] nums) {
        StringBuilder ans = new StringBuilder();
        if (nums.length == 0)
            return ans.toString();
        ans.append(nums[0]);
        if (nums.length == 1)
            return ans.toString();
        if (nums.length == 2) {
            ans.append("/").append(nums[1]);
            return ans.toString();
        }
        ans.append("/(").append(nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            ans.append("/").append(nums[i]);
        }
        ans.append(")");
        return ans.toString();
    }

    class Result {
        String str;
        double val;
    }

    public String optimalDivision1(int[] nums) {
        int len = nums.length;
        return getMax(nums, 0, len - 1).str;
    }

    private Result getMax(int[] nums, int start, int end) {
        Result r = new Result();
        r.val = -1.0;
        if (start == end) {
            r.str = nums[start] + "";
            r.val = nums[start];
        } else if (start + 1 == end) {
            r.str = nums[start] + "/" + nums[end];
            r.val = (double)nums[start] / (double)nums[end];
        } else {
            for (int i = start; i < end; i++) {
                Result r1 = getMax(nums, start, i);
                Result r2 = getMin(nums, i + 1, end);
                if (r1.val / r2.val > r.val) {
                    r.str = r1.str + "/" + (end - i >= 2 ? "(" + r2.str + ")" : r2.str);
                    r.val = r1.val / r2.val;
                }
            }
        }

        //System.out.println("getMax " + start + " " + end + "->" + r.str + ":" + r.val);
        return r;
    }

    private Result getMin(int[] nums, int start, int end) {
        Result r = new Result();
        r.val = Double.MAX_VALUE;

        if (start == end) {
            r.str = nums[start] + "";
            r.val = nums[start];
        }
        else if (start + 1 == end) {
            r.str = nums[start] + "/" + nums[end];
            r.val = (double)nums[start] / (double)nums[end];
        }
        else {
            for (int i = start; i < end; i++) {
                Result r1 = getMin(nums, start, i);
                Result r2 = getMax(nums, i + 1, end);
                if (r1.val / r2.val < r.val) {
                    r.str = r1.str + "/" + (end - i >= 2 ? "(" + r2.str + ")" : r2.str);
                    r.val = r1.val / r2.val;
                }
            }
        }

        //System.out.println("getMin " + start + " " + end + "->" + r.str + ":" + r.val);
        return r;
    }

    public static void main(String[] args) {
        List<String> nanmes = Arrays.asList("liudun", "zahngsan");
        System.out.println(String.join(",", nanmes));
    }

}
