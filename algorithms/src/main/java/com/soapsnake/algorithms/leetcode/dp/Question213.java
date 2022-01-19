package com.soapsnake.algorithms.leetcode.dp;

public class Question213 {

    public static void main(String[] args) {
        Question213 question213 = new Question213();
        int[] nums = {1, 2, 1, 1};
        System.out.println(question213.rob(nums));
    }

    //leetcode213,抢银行问题,dp解法,连续的房子不能被抢
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length < 2)
            return nums[0];

        System.out.println("");

        //第一个DP数组
        int[] startFromFirstHouse = new int[nums.length + 1];  //该数组用来存从0开始的结果
        //第二个DP数组
        int[] startFromSecondHouse = new int[nums.length + 1];  //该数组用来存从1开始的结果

        //dp[i]代表抢劫i栋房子的最大收益,dp[0]代表抢劫0栋房子的收益,必然是0
        startFromFirstHouse[0] = 0;
        //从第一间房子开始抢劫,那么抢劫1坐房子的收益=第一间房子的财宝
        startFromFirstHouse[1] = nums[0];   //后续2或者3

        startFromSecondHouse[0] = 0;
        //从第二件房子才开始抢劫,那么第一座房子就放过了,相当于一座没抢,收益还是0
        startFromSecondHouse[1] = 0;     //后续3或者4

        for (int i = 2; i <= nums.length; i++) {
            //从第一座房子开始抢劫,抢劫到第i坐房子的收益 = max(不抢劫i座房子(收益同i-1) vs 抢劫i-2座房子+i房子财宝价值)
            startFromFirstHouse[i] = Math.max(startFromFirstHouse[i - 1], startFromFirstHouse[i - 2] + nums[i - 1]);

            //从第二座房子开始抢劫,抢劫到第i坐房子的收益 = max(不抢劫i-1座房子 vs 抢劫i-1座房子)
            startFromSecondHouse[i] = Math.max(startFromSecondHouse[i - 1], startFromSecondHouse[i - 2] + nums[i - 1]);
        }

        return Math.max(startFromFirstHouse[nums.length - 1], startFromSecondHouse[nums.length]);
    }

    class Tuple {
        private int imax;
        private int imin;

        Tuple(int imax, int imin) {
            this.imax = imax;
            this.imin = imin;
        }
    }

    //关键点就是这句话：“由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin。”
    public int maxProduct(int[] nums) {
        Tuple[] dp = new Tuple[nums.length + 1];
        dp[0] = new Tuple(nums[0], nums[0]);
        int maxRes = dp[0].imax;
        for (int i = 1; i < nums.length; i++) {
            Tuple prev = dp[i - 1];
            int curMax = Math.max(Math.max(nums[i], prev.imax * nums[i]), nums[i] * prev.imin);
            int curMin = Math.min(Math.min(nums[i], prev.imin * nums[i]), nums[i] * prev.imax);
            dp[i] = new Tuple(curMax, curMin);
            maxRes = Math.max(maxRes, curMax);
        }
        return maxRes;
    }


}
