package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.list.ListNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-11-27
 * JavaRoad
 */
public class WeeklyContext321 {

    public int pivotInteger(int n) {
        // sum( 1 -> x) == sum(x -> n);
        int[] sum = new int[n + 1];
        int total = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + i;
            total += i;
        }
        for (int i = 1; i <= n; i++) {
            if (sum[i] == total - sum[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    public int appendCharacters(String s, String t) {
        String s1 = s;
        String s2 = t;
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[N][M]);
        return M - dp[N][M];
    }

    public ListNode removeNodes(ListNode head) {
        //如果节点右侧还有更大值节点,这个节点就需要被移除
        return null;
    }

    // 返回使得 nums 递增需要的最小交换元素次数
    public static int minChanges(int[] nums){
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<copy.length; i++){
            map.put(copy[i], i);
        }
        System.out.println("map = " + map);
        boolean[] flag = new boolean[nums.length];  // 用于标记 nums[i] 是否已经被加入环中
        int loop = 0; // 环的个数
        for(int i=0; i<nums.length; i++){
            if(!flag[i]){
                int j = i;
                System.out.println("i = " + i + " j = " + j + " ");
                while(!flag[j]){ // 画环
                    int index = map.get(nums[j]); // 当前节点指向的位置，画环过程
                    flag[j] = true; // 将 j 加入环中
                    j = index; // 将当前节点移动到环上下个节点
                }
                loop++; // 环数递增
            }
        }
        return nums.length - loop; // 最小交换次数为 ： 数组长度 - 环数
    }

    public static void main(String[] args) {
        WeeklyContext321 w = new WeeklyContext321();
        int[] a = {7, 6, 8, 5};
        System.out.println(minChanges(a));
    }
}
