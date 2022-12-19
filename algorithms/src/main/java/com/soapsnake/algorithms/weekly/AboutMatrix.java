package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Created on 2022-01-28
 */
public class AboutMatrix {

    //630
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int total = 0;
        for (int[] course : courses) {
            int ti = course[0];
            int di = course[1];
            if (total + ti <= di) {
                total += ti;
                queue.offer(ti);
            } else if (!queue.isEmpty() && queue.peek() > ti) {
                total -= queue.poll() - ti;
                queue.offer(ti);
            }
        }
        return queue.size();
    }

    public int rectangleArea(int[][] rectangles) {
        int mod = (int) Math.pow(10, 9) + 7;
        long res = 0;
        List<int[]> recList = new ArrayList<>();
        for (int[] rec : rectangles)
            addRectangle(recList, rec, 0);

        for (int[] rec : recList)
            res = (res + ((long) (rec[2] - rec[0]) * (long) (rec[3] - rec[1]))) % mod;

        return (int) res % mod;
    }

    public void addRectangle(List<int[]> recList, int[] curRec, int start) {
        if (start >= recList.size()) {
            recList.add(curRec);
            return;
        }

        int[] r = recList.get(start);

        if (curRec[2] <= r[0] || curRec[3] <= r[1] || curRec[0] >= r[2] || curRec[1] >= r[3]) {
            addRectangle(recList, curRec, start + 1);
            return;
        }

        if (curRec[0] < r[0])
            addRectangle(recList, new int[]{curRec[0], curRec[1], r[0], curRec[3]}, start + 1);

        if (curRec[2] > r[2])
            addRectangle(recList, new int[]{r[2], curRec[1], curRec[2], curRec[3]}, start + 1);

        if (curRec[1] < r[1])
            addRectangle(recList, new int[]{Math.max(r[0], curRec[0]), curRec[1], Math.min(r[2], curRec[2]), r[1]}, start + 1);

        if (curRec[3] > r[3])
            addRectangle(recList, new int[]{Math.max(r[0], curRec[0]), r[3], Math.min(r[2], curRec[2]), curRec[3]}, start + 1);
    }


    //2132
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m + 1][n + 1];
        int[][] diff = new int[m + 1][n + 1];
        for(int i = 1; i <= m;i++){
            for(int j = 1; j <= n;j++){
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n;j++){
                if(grid[i][j] == 0){
                    int x = i + stampHeight, y = j + stampWidth ;
                    if(x <= m && y <= n && (sum[x][y] - sum[x][j] - sum[i][y] + sum[i][j] == 0)){
                        diff[i][j]++;
                        diff[i][y]--;
                        diff[x][j]--;
                        diff[x][y]++;
                    }
                }
            }
        }
        int[] cnt = new int[n + 1];
        int[] pre = new int[n + 1];
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n;j++){
                cnt[j + 1] = cnt[j] + pre[j + 1] - pre[j] + diff[i][j];
                if(cnt[j + 1] == 0 && grid[i][j] == 0) return false;
            }
            int[] tmp = cnt;
            cnt = pre;
            pre = tmp;
        }
        return true;
    }
}
