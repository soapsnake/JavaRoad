package com.soapsnake.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class Question152 {

    /**
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     */

    //这个解法是对的,但是超时了
    public int maxProduct(int[] nums) {
        //思路1.左右指针,先算所有数字的总乘积,然后,每次踢掉左右的一个数字,直到只剩一个数字,不靠谱

        //思路2.求全排列,然后计算所有全排列的乘积最大值,这个还行吧
        if (nums.length == 1) {
            return nums[0];
        }
        backTrace(nums, new ArrayList<>(), 0, 0);
        return this.max;
    }

    int max = 0;
    private void backTrace(int[] nums, List<Integer> tmp, int index, int pre) {
        System.out.println(tmp);
        if (tmp.size() > nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (tmp.size() == 0) {
                tmp.add(nums[i]);
                this.max = Math.max(nums[i], max);
            }else if (tmp.get(tmp.size() - 1) == nums[i - 1] && i == pre + 1) {
                tmp.add(nums[i]);
                int product = getProduct(tmp);
                this.max = Math.max(product, max);
            } else {
                continue;
            }
            backTrace(nums, tmp, i + 1, i);
            tmp.remove(tmp.size() - 1);
        }
    }

    private int getProduct(List<Integer> tmp) {
        int res = 1;
        for (int i : tmp) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        Question152 question152 = new Question152();
        int[] nums = {3,-2,-3,-3,1,3,0};
        System.out.println(question152.maxProduct2(nums));
    }

    //动态规划算法,实在太他妈难理解了
    public int maxProduct2(int[] nums) {
        int r = nums[0];

        //imax和imin保存的是0 -> i - 1的所有子数组的最大/最小乘积
        for (int i = 1, imax = r, imin = r; i < nums.length; i++) {
            System.out.println("i =, r =, imax = , imin = " + i + "," + r + ", " + imax + "," + imin);
            if (nums[i] < 0) {  //一旦nums[i]小于0那么原来的最大值会变成最小值,原来的最小值会变成最大值
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            //再一次强调,imax和imin保存的是0 -> i-1的所有子数组中的最大/最小乘积

            imax = Math.max(nums[i], imax * nums[i]);//如果取得是nums[i],那么表明最大乘积子数组的开始索引要变成新的了
            imin = Math.min(nums[i], imin * nums[i]);//如果取得是nums[i],那么表明最小乘积子数组的开始索引也要变成新的了
            r = Math.max(r, imax);
        }
        return r;
    }


    /**
     * 标准Dp解法
     * 这道题妙就妙在它不仅仅依赖了一个状态（前一个数所能获得的最大乘积），而是两个状态（最大和最小乘积）。比较简单的dp问题可能就只是会建立一个dp[]，然后把最大值放到其中。
     * 但是这道题给我们打开了新的思路：我们的dp数组里面可以存更多的信息。而上面的解法之所以没有用dp数组的原因是dp[i]只依赖于dp[i - 1]因此没有必要把前面所有的信息都存起来，
     * 只需要存前一个dp[i-1]的最大和最小的乘积就可以了。下面的代码使用了自定义的内部类Tuple,从而可以同时存imax和imin,并将所有的imax和imin存到了dp数组中。
     * 虽然稍显复杂，但是有助于加深理解。
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        Tuple[] dp = new Tuple[nums.length];
        dp[0] = new Tuple(nums[0],nums[0]);
        int res = dp[0].imax;
        for (int i = 1; i < nums.length; i++) {
            Tuple prev = dp[i - 1];
            int imax = Math.max(Math.max(nums[i], nums[i] * prev.imax) , nums[i] * prev.imin);
            int imin = Math.min(Math.min(nums[i], nums[i] * prev.imax) , nums[i] * prev.imin);
            dp[i] = new Tuple(imax,imin);
            res = Math.max(imax, res);
        }
        return res;
    }

    private class Tuple {
        private int imax;
        private int imin;
        private Tuple(int imax, int imin) {
            this.imax = imax;
            this.imin = imin;
        }
    }


}
