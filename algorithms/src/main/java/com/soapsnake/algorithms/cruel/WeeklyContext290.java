package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
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
        System.out.println(weeklyCOntext290.countLatticePoints(circles));
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


}
