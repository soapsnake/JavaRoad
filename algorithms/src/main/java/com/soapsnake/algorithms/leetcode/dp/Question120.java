package com.soapsnake.algorithms.leetcode.dp;

import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-14 00:45
 */
public class Question120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        //动态规划,关键问题是如何找到上一排节点对应的下一排子节点
        //如果在上一排节点是第0个,那么下一排的0, 1,如果是1了,对应下一排的1和2
        //如果从上往下,这种思路只适用于每一层都是正数的情况,一旦是有负数,就会出现局部最优不是全局最优的情况
        //所以我们应该从下往上,先找出最底层的最小数字i,然后往上倒,找上一层i - 1,i中比较小的数字,一层一层晚上往上
        //类似五子棋多叉树的回溯思想

        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

                //第i行第j个元素的最小值,应该等于第i + 1行第j个元素和第j+ 1个元素中较小的那个数
                int min = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));

                //把这个最小值放进i行第j个元素当中,那么第i - 1行就可以根据第i的结果进行计算,层层往上,就可以算出第0层的最小值
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(0).get(0);
    }

    //求金字塔顶到塔底的最小路径和
    public int minimumTotal2(List<List<Integer>> trangle) {
        for (int i = trangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                //第i行第j个元素的最小值,应该等于第i + 1行第j个元素和第j+ 1个元素中较小的那个数
                int min = Math.min(trangle.get(i + 1).get(j), trangle.get(i + 1).get(j + 1));

                //把这个最小值放进i行第j个元素当中,那么第i - 1行就可以根据第i的结果进行计算,层层往上,就可以算出第0层的最小值
                trangle.get(i).set(j, trangle.get(i).get(j) + min);
            }
        }
        return trangle.get(0).get(0);
    }

}
