package com.soapsnake.algorithms.cruel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-10
 * JavaRoad
 */
public class Others {

    int mod = (int) 1e9 + 7;


    public static void main(String[] args) {
        System.out.printf("%.18f", 1e-8);

        double d = 0.1234;
        System.out.printf("%.18f", d);

        BigDecimal bigDecimal  = new BigDecimal("123");
        //int[] remo = new int[] {3, 1};
        //StringBuilder s = new StringBuilder("abcacb");
        //for (int n : remo) {
        //    s.replace(n, n + 1, "*");
        //}
        //
        //
        //
        //System.out.println(s.toString());
    }

    //1452
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        //类似倒排索引,  apple -> [0,1], amozon -> [0,1], google -> [1,2]
        //{{apple, amazon}, {apple, amazon, google}, {google}}
        // [0,1] [0,1]  如果序列的每一个全部都有对应的, 说明是
        //[0,1][0,1][1,2]
        int n = favoriteCompanies.size();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean find = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Set<String> temp = new HashSet<>(favoriteCompanies.get(j));
                List<String> coms = favoriteCompanies.get(i);
                if (temp.containsAll(coms)) {
                    find = false;
                    break;
                }
            }
            if (find) {
                res.add(i);
            }
        }
        return res;
    }

    public int numberOfWays(String corridor) {
        int count = 0;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                count++;
            }
        }
        if (count == 0 || count % 2 == 1) {
            return 0;
        }
        long ans = 1;
        int i = 0;
        while (corridor.charAt(i) == 'P') {
            i++;
        }//i定位于第一个S
        while (count > 2) {
            int j = i + 1, p = 0;//p记录S之间的P
            while (corridor.charAt(j) == 'P') {
                j++;
            }//j定位于后一个S
            j++;//定位于后一个S的后边
            while (corridor.charAt(j) == 'P') {
                j++;
                p++;
            }
            ans = (ans * (p + 1)) % mod;
            count -= 2;
            i = j;
        }
        return (int) ans;
    }

    public int numberOfWays2(String corridor) {
        int i = 0;
        int noOfPlants = 1; //植物的数量
        int noOfSeats = 0;  // 座位的数量
        int count = 0;
        long ans = 1;  //最终结果
        int mod = (int) 1e9 + 7;
        while (i < corridor.length()) {
            char ch = corridor.charAt(i);
            if (count < 2) {
                if (ch == 'S') {
                    count++;
                    noOfSeats++;
                }
            } else {
                if (ch == 'S') {
                    ans = ans * noOfPlants % mod;
                    noOfPlants = 1;
                    noOfSeats++;
                    count = 1; //这个我感觉完全
                } else {
                    noOfPlants++;
                }
            }
            i++;
        }
        if (noOfSeats == 0 || noOfSeats % 2 != 0) {
            return 0;
        }
        return (int) ans;
    }

    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] inter = intervals[i];
            map.put(inter[0], i);
        }

        int[] res = new int[intervals.length];
        Arrays.sort(intervals, Collections.reverseOrder());
        Arrays.fill(res, -1);
        for (int i = 0; i < intervals.length; i++) {
            //map.ceilingEntry 返回大于给定key的最小的一个key
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(intervals[i][1]);
            if (entry != null) {
                res[i] = entry.getValue();
            }
        }
        return res;
    }

    public int[] findPeakElement(int[][] mat) {
        int startCol = 0, endCol = mat[0].length - 1;
        while (startCol <= endCol) {
            int midCol = startCol + (endCol - startCol) / 2;
            int maxRow = 0;
            for (int row = 0; row < mat.length; row++) {
                if (mat[row][midCol] > mat[maxRow][midCol]) {
                    maxRow = row;
                }
            }
            String str = "";
            StringBuilder sv = new StringBuilder();
            sv.replace(1,2,"*");
            //以下三个element均处于同一行
            int currElement = mat[maxRow][midCol];
            int leftElement = midCol == 0 ? -1 : mat[maxRow][midCol - 1];
            int rightElement = midCol == mat[0].length - 1 ? -1 : mat[maxRow][midCol + 1];

            if (currElement > leftElement && currElement > rightElement) {
                // currElement is peak element
                return new int[]{maxRow, midCol};
            } else if (leftElement > currElement) {
                endCol = midCol - 1;
            } else {
                startCol = midCol + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public int maximumScore(int[] scores, int[][] edges) {
        //读题: 无向图, 要求返回一个长度为4的序列,这个序列的和最大,如果没有长度为4的序列那么返回0,
        //思路: 枚举每个点
        int n = scores.length;
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, x -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph[x].add(new int[] {scores[y], y});
            graph[y].add(new int[] {scores[x], x});
        }
        for (int i = 0; i < n; i++) {
            if (graph[i].size() > 3) {
                graph[i].sort((a, b) -> b[0] - a[0]);
                graph[i] = new ArrayList<>(graph[i].subList(0, 3));
            }
        }
        int ans = -1;
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            for (int[] p : graph[x]) {
                //枚举xy边所有与x点相邻的点
                int a = p[1];
                for (int[] q : graph[y]) {
                    //枚举xy边所有与y点相邻的点
                    int b = q[1];
                    if (a != y && b != x && a != b) {
                        ans = Math.max(ans, p[0] + scores[x] + scores[y] + q[0]);
                    }
                }
            }
        }
        return ans;
    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int j = 0, c = 0;
        for (int t : buses) {
            for (c = capacity; c > 0 && j < passengers.length && passengers[j] <= t; --c) {
                ++j;
            }
        }
        --j;
        int ans = c > 0 ? buses[buses.length - 1] : passengers[j];
        while (j >= 0 && passengers[j--] == ans) --ans;
        return ans;
    }
}