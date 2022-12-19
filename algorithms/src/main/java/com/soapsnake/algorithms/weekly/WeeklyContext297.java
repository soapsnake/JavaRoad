package com.soapsnake.algorithms.weekly;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-06-12
 * JavaRoad
 */
public class WeeklyContext297 {


    public static void main(String[] args) {
        WeeklyContext297 weeklyContext297 = new WeeklyContext297();
        int[][] num1 = {{5, 3}, {4, 0}, {2, 1}};
        int[][] num2 = {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};
        System.out.println(weeklyContext297.minPathCost(num1, num2));

        String sb = "";
        sb.substring(1);
        //读题:

    }

    public double calculateTax(int[][] brackets, int income) {
        //读题: bigdecimal:
        BigDecimal ans = new BigDecimal(0);
        int prev = 0;
        for (int i = 0; i < brackets.length; i++) {
            int am = brackets[i][0];
            BigDecimal per = new BigDecimal(brackets[i][1]).divide(BigDecimal.valueOf(100), 6, RoundingMode.UP);
            int should = Math.min(income, am - prev);
            if (should <= 0) {
                break;
            }
            prev = am;
            income -= should;
            ans = ans.add(new BigDecimal(should).multiply(per));
        }
        return ans.doubleValue();
    }

    public int minPathCost(int[][] grid, int[][] moveCost) {
        //读题: 从第0行到n - 1行,求最小路径+最小代价之和
        //i行j列 -> i + 1 行
        //i = 0 -> grid[0][j] + moveCost[0][j]
        //dp[i][j] = Math.min(dp[i - 1][*] + grid[i][*] + moveCost[i - 1][*]);
        //ans = Math.min(dp[m - 1][j];
        int m = grid.length;  //行
        int n = grid[0].length;  //列
        int[][] dp = new int[m][n];
        System.arraycopy(grid[0], 0, dp[0], 0, n);
        System.out.println(Arrays.deepToString(dp));

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curMin = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    curMin = Math.min(curMin, dp[i - 1][k] + moveCost[grid[i - 1][k]][j]);
                }
                dp[i][j] += curMin + grid[i][j];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        for (int i = 0; i < n; i++) {
            ans = Math.min(dp[m - 1][i], ans);
        }
        return ans;
    }

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;
        if (flowers[0] >= target) return (long) n * full;

        long[] sum = new long[n];
        sum[0] = flowers[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + flowers[i];
        }
        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (flowers[i] >= target) continue;
            long l = flowers[0], r = target - 1;
            while (l < r) {
                long mid = (r - l + 1) / 2 + l;
                if (check(flowers, sum, newFlowers, mid, i)) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            ans = Math.max(ans, (n - i - 1) * (long) full + l * partial);
            newFlowers -= target - flowers[i];
            if (newFlowers < 0) return ans;
        }
        if (newFlowers >= 0) return Math.max(ans, (long) full * n);
        return ans;
    }

    public boolean check(int[] flowers, long[] sum, long newFlowers, long minVal, int idx) {
        int l = 0, r = idx;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (flowers[mid] < minVal) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        long diff = minVal * (l + 1) - sum[l];
        return newFlowers >= diff;
    }

    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        boolean[][] visited = new boolean[m][n];
        LinkedList<Point> list = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        list.offer(new Point(start[0], start[1]));
        while (!list.isEmpty()) {
            Point p = list.poll();
            int x = p.x, y = p.y;
            for (int i = 0; i < 4; i++) {
                int xx = x, yy = y;
                while (xx >= 0 && xx < m && yy >= 0 && yy < n && maze[xx][yy] == 0) {
                    xx += dir[i][0];
                    yy += dir[i][1];
                }
                xx -= dir[i][0];
                yy -= dir[i][1];
                if (visited[xx][yy]) continue;
                visited[xx][yy] = true;
                if (xx == destination[0] && yy == destination[1]) return true;
                list.offer(new Point(xx, yy));
            }
        }
        return false;
    }

    class Point {
        int x, y;

        public Point(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

}


