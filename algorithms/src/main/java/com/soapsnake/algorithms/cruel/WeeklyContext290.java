package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-04-24
 * JavaRoad
 */
public class WeeklyContext290 {


    private static final int[] mx = {-1, 0, 1, 0};
    private static final int[] my = {0, -1, 0, 1};

    public static void main(String[] args) {
        WeeklyContext290 weeklyCOntext290 = new WeeklyContext290();
        int[][] circles = {{2, 2, 1}};
        //System.out.println(weeklyCOntext290.countLatticePoints(circles));

        //StringBuilder sb = new StringBuilder("Hello");
        int a = 10;
        int b = 12;
        System.out.println("before swap, a = 10, b = 12");

        weeklyCOntext290.swap(a, b);
        System.out.println("after swap, a = " + a + " b = " + b);

        Integer c = 14;
        Integer d = 15;
        System.out.println("before swap, c = 14, d = 15");
        weeklyCOntext290.swap(c, d);
        System.out.println("after swap, c = " + c + " d = " + d);
    }


    public void swap(Object a, Object b) {
        Object tmp = a;
        a = b;
        b = tmp;
    }

    public List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        //如果
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] arr : nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : arr) {
                set.add(num);
            }
            for (int tmp : set) {
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= n) {
                list.add(entry.getKey());
            }
        }
        list.sort(Integer::compareTo);
        return list;
    }

    public int countLatticePoints(int[][] circles) {
        //遍历每个圆,沿圆心向四周扩散,只要在半径之内的点都可以
        //用字符串格式  x-y保存点,去重
        Set<String> set = new HashSet<>();
        //点到圆心的距离只要是小于半径都是在园内的,
        for (int[] circle : circles) {
            Set<String> find = findCircle(circle);
            set.addAll(find);
        }
        return set.size();
    }

    private Set<String> findCircle(int[] circle) {
        int x = circle[0];
        int y = circle[1];
        int r = circle[2];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{x, y});
        Set<String> res = new HashSet<>();
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (!res.add(cur[0] + "-" + cur[1])) continue;
            for (int i = 0; i < 4; i++) {
                int curx = cur[0] + mx[i];
                int cury = cur[1] + my[i];
                double dis = calculate(x, curx, y, cury);
                if (dis <= r) {
                    que.offer(new int[]{curx, cury});
                }
            }
        }
        return res;
    }

    private double calculate(int x, int curx, int y, int cury) {
        return Math.hypot(Math.abs(x - curx), Math.abs(y - cury));
    }

    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = rectangles.length;
        //哈希表key为高度，List为宽度的集合。
        //高度的范围在0 -> 100, 所以这里的map的key只有100个
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int l = rectangles[i][0];  //第i个长方形的长
            int h = rectangles[i][1];  //第i个长方形的高
            //所有高度为h的长方形的长度集合
            map.computeIfAbsent(h, x -> new ArrayList<>()).add(l);
        }
        //由于需要对宽度的列表使用二分法，所以需要排序。
        for (int key : map.keySet()) {
            //把所有对应h高度的长度列表进行从小到大的排序
            Collections.sort(map.get(key));
        }

        int[] ans = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int[] p = points[i];
            int x = p[0];  //长度
            int y = p[1];  //高度
            int count = 0;
            //本题重点，枚举可能的高度，并找到当前高度下，合法的矩形数量。
            for (int h = 100; h >= 1; h--) {
                //枚举高度已经比point的高度还小了，不可能再找到合法矩形了。
                if (h < y) break;
                //不存在以当前h为高度的矩形，跳过。
                if (!map.containsKey(h)) continue;  //如果cointain其实是边界情况,就是点刚好在边上
                //二分搜索，并累加答案
                count += binarySearch(map.get(h), x);  //二分搜索长度刚好 >=x 的数量,很好办,求索引,然后length - index + 1;
            }
            ans[i] = count;
        }
        return ans;

    }

    public int binarySearch(List<Integer> nums, int k) {
        //二分法找nums中有多少元素大于等于k。
        int n = nums.size();
        int left = 0;
        int right = n - 1;
        while (left < right) {  //记这个模板吧
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums.get(right) >= k ? n - right : 0; //如果结果比k小那么说明所有的数字都比k小
    }

    private int isin(int[][] rectangles, int[] point) {
        int res = 0;
        for (int[] rec : rectangles) {
            int x = rec[0];
            int y = rec[1];
            if (point[0] <= x && point[1] <= y) {
                res++;
            }
        }
        return res;
    }

    public int[] minDifference(int[] nums, int[][] queries) {
        int[][] count = new int[nums.length][101];
        int[] tmp = new int[101];
        for(int i = 0 ; i < nums.length ; i++){
            tmp[nums[i]]++;
            count[i] = tmp.clone();
        }
        int n = queries.length;
        int[] res = new int[n];
        Arrays.fill(res , 105);
        for(int i = 0 ; i < n ; i++){
            int[] copy = queries[i];
            int l = copy[0] , r = copy[1];
            tmp = new int[101];
            int pre = 0;
            tmp[nums[l]]++;
            for(int j = 0 ; j < 101 ; j++){
                tmp[j] += count[r][j] - count[l][j];
                if(tmp[j] > 0){
                    if(pre == 0){
                        pre = j;
                    }else{
                        res[i] = Math.min(j-pre , res[i]);
                        pre = j;
                    }
                }
            }
            res[i] = res[i] == 105 ? -1 : res[i];
        }
        return res;
    }


    /**
     * The idea is a simple trick. First, you notice that at every single element in our original nums array,
     * you have 2 choices: To earn or not to earn. Based on problem, whichever element you earn, you must delete
     * any values of nums[i]-1 and nums[i]+1. It helps to assume a sorted array so that you can place elements
     * in ascending order to visualize the problem. You notice there that if you earn an element,
     * you cannot earn its immediate unequal neighbors on both sides.
     * You also notice that if you have duplicate values in nums array, if you earn one of them, you end up
     * earning all of them. This is because you have deleted its neighbors and therefore make
     * its remaining duplicates "undeletable". This is important because you notice the problem simplifies to which
     * values can earn you the largest total.
     * So I aggregated the sums into a sums array to map each value (array's index) with the total sum you can earn by
     * deleting all elements of that value (array's value). Then write a for loop to compute the maximum sum ending
     * at i At each step, your sum can either depend on your previous sum or the prior plus the current.
     * You use a greedy algorithm to always pick the maximum value for each i.
     * *** Notice that when you create sums array, it naturally orders (sorts) the elements for you in ascending order
     * so you can traverse it and get its immediate unequal neighbors on both sides in O(1).
     *
     * sum[i] = Max(sum[i-1], sum[i-2] + sum[i])
     * class Solution {
     *     public int deleteAndEarn(int[] nums) {
     *         int[] sum = new int[10002];
     *
     *         for(int i = 0; i < nums.length; i++){
     *             sum[nums[i]] += nums[i];
     *         }
     *
     *         for(int i = 2; i < sum.length; i++){
     *             sum[i] = Math.max(sum[i-1], sum[i-2] + sum[i]);
     *         }
     *         return sum[10001];
     *     }
     * }
     */


}
