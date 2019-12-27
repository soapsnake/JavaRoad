package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import javax.activation.FileDataSource;

public class Question494 {

    public static void main(String[] args) {
        Question494 question494 = new Question494();
        int[] nums = {1, 1, 1, 1, 1};
        int s = 3;
        System.out.println(question494.findTargetSumWays2(nums, s));
    }

    public int findTargetSumWays(int[] nums, int S) {
        List<List<Integer>> res = new ArrayList<>();
        backTrace(nums, S, new ArrayList<>(), 0, res, 0);
        return res.size();
    }

    private void backTrace(int[] nums, int total, List<Integer> temp, int sum, List<List<Integer>> res, int start) {
        if (temp.size() == nums.length) {
            if (sum == total) {
                res.add(new ArrayList<>(temp));
                return;
            }
        }

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backTrace(nums, total, temp, sum + nums[i], res, i + 1);
            backTrace(nums, total, temp, sum - nums[i], res, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int n: nums){
            sum += n;
        }
        //因为nums给的都是正数,所以sum只可能会落在 -sum  ~ sum 之间,所以才有这个判断
        // 举个例子:
        //{1,1,1,1,1} sum = 5, 那么S只能落在 -5 ~ 5之间的闭区间上面
        if (S < -sum || S > sum) {
            return 0;
        }
        //dp的含义dp[i][j] = ways  i表示nums的前i个数字, j表示总和, ways表名前i个数字和达到j的方式数
        //那么这个2 * sum + 1如何理解,还是前面的{1,1,1,1,1} 那么其和会分布在 -5 ~ 5上,所以一共是有11种情况的
        int[][] dp = new int[nums.length + 1][ 2 * sum + 1];
        // 0 + sum 就是sum数组的中间值也就是0 ->  {-sum...., 0, ....sum}
        dp[0][0 + sum] = 1;
        int leftBound = 0;  //sum的最小取值索引
        int rightBound = sum * 2;  //sum的最大取值索引
        for (int i = 1; i <= nums.length; i++) {
            for (int j = leftBound; j < rightBound + 1; j++) { //j就是current sum
                if (j + nums[i - 1] <= rightBound) {  //如果当前和 + nums[i-1]仍然在合理范围内,那么就可以一直做加法
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= leftBound) {  //如果当前和 - nums[i-1]仍然在合理范围内,那么就可以一直做减法
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum + S];
    }
}
