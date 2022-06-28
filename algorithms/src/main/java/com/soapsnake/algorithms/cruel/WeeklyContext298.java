package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class WeeklyContext298 {
    
    public String greatestLetter(String s) {
        //读题: 1. 结果最多一个,要大写. 
        //字符必须大小写都出现过, 
        //如果有多个都满足,返回字母表序列最前的
        int[] small = new int[26];
        int[] big = new int[26];
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                small[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                big[c - 'A']++;
            }
        }
        List<Character> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (small[i] > 0 && big[i] > 0) {
                res.add((char) ('A' + i));
            }
        }
        if (res.size() == 0) return "";
        Collections.sort(res);
        return res.get(res.size() - 1) + "";
    }

    public int minimumNumbers(int num, int k) {
        //1.求得是一个list的最小size, list中每个数字各位数都要是k, list所有数字相加得num
        //思路: 先把1 -> num所有尾数是k的数字全部枚举出来,然后使用coin change(同一枚硬币有无数枚)
        int[] tmp;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (i % 10 == k) {
                list.add(i);
            }
        }
        tmp = list.stream().mapToInt(x -> x).toArray();
        return coinChange(tmp, num);
    }

    private int coinChange(int[] coins, int amount) {
        //dp[i] = 金额达到i需要的最少coin数量
        //dp[i] = dp[i - coins[k]] + 1;
        //dp[i] = i;
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < coins.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }
        return dp[amount];
    }

    public int longestSubsequence(String s, int k) {
        int zeros = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            }
        }
        int ones = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (Long.parseLong(s.substring(i), 2) > k) {
                    break;
                } 
                ones++;
            }
        }
        return zeros + ones;
    }



    public int longestSubsequence2(String s, int k) {
        //dp[i] 到索引i时的最长串的长度 dp[n]  new int[n + 1] 
        //dp[i] -> max(dp[i - 1] + 1, dp[i - 1])
        //dp[0] = 1,  
        String prev = "";
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {

            //每一个都有取和不取两个状态;
            
            if (check(prev + s.charAt(i - 1), k)) {
                dp[i] = dp[i - 1] + 1; //
                prev += s.charAt(i - 1);
                System.out.println(prev);
            } else {
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return prev.length();
    }
    private boolean check(String string, int k) {
        boolean res = Integer.parseInt(string, 2) <= k;
        System.out.println("s = " + string + "  => res = " + res);
        return  res;
    }

    public static void main(String[] args) throws Exception {
        WeeklyContext298 week = new WeeklyContext298();
        String s = "00101001";
        int k = 1;
        System.out.println(week.longestSubsequence(s, k));
    }


    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            dfs(i, new boolean[n], bombs);
            ans = Math.max(ans, count);
        }
        return ans;
    }
    int count = 0;
    private void dfs(int idx, boolean[] v, int[][] bombs) {
        count++;
        v[idx] = true;
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            if (!v[i] && inRange(bombs[idx], bombs[i])) {
                v[i] = true;
                dfs(i, v, bombs);
            }
        }
    }

    private boolean inRange(int[] a, int[] b) {
        long dx = a[0] - b[0], dy = a[1] - b[1], r = a[2];
        return dx * dx + dy * dy <= r * r;
    }


    //Time : O(n^2) ?
    //Space: O(m*n) ?
    char[][] grid;
    int m, n;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length; 
        n = grid[0].length;
        int step = 0;
        boolean[][][] visited = new boolean[m][n][4]; // considering 4 directons
        
        Queue<int[]> boxQ = new LinkedList<>();
        Queue<int[]> playerQ = new LinkedList<>();
        int[] boxLoc=new int[2], targetLoc=new int[2] , playerLoc=new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //箱子的初始位置
                if (grid[i][j] == 'B') boxLoc = new int[]{i, j};
                //目标位置
                if (grid[i][j] == 'T') targetLoc = new int[]{i, j};
                //人的位置
                if (grid[i][j] == 'S') playerLoc = new int[]{i, j};
            }
        }
        boxQ.offer(new int[]{boxLoc[0], boxLoc[1]});
        playerQ.offer(new int[]{playerLoc[0], playerLoc[1]});
        
        while (!boxQ.isEmpty()) { 
            for (int i = 0, l = boxQ.size(); i < l; i++) { //as we care about all directions, it should be like this.--> it's related to calculating 'step'
                int[] currBoxLoc = boxQ.poll();
                int[] currPlayerLoc = playerQ.poll();
                //已经到达目标位置,返回路径
                if (currBoxLoc[0] == targetLoc[0] && currBoxLoc[1] == targetLoc[1]) return step; 
                for (int j = 0; j < dir.length; j++) { // Checking all directions
                    if (visited[currBoxLoc[0]][currBoxLoc[1]][j]) continue;
                    int[] d = dir[j];

                    //根据箱子位置计算人的位置,没地方站就没法推
                    int r0 = currBoxLoc[0] + d[0];
                    int c0 = currBoxLoc[1] + d[1];
                    //人没地方站,放弃
                    if (r0 < 0 || r0 >= m || c0 < 0 || c0 >= n || grid[r0][c0] == '#') continue;
                    
                    //箱子被推之后的新位置
                    int r = currBoxLoc[0] - d[0], c = currBoxLoc[1] - d[1]; 

                    //新位置是墙,放弃
                    if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '#') continue; 

                    //如果新的位置人过不去,也放弃
                    if (!reachable(r0, c0, currBoxLoc, currPlayerLoc)) continue;
                    
                    //把箱子推走后,人会来到原来箱子所处的位置,visit其实记录的是人的访问路径
                    visited[currBoxLoc[0]][currBoxLoc[1]][j] = true;
                    boxQ.offer(new int[]{r,c});
                    playerQ.offer(new int[]{currBoxLoc[0],currBoxLoc[1]}); 
                }
            }
            step++;
        }
        return -1;
    }
    
    private boolean reachable(int i, int j, int[] boxLoc, int[] playerLoc) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(playerLoc);
        boolean[][] visited = new boolean[m][n];
        visited[boxLoc[0]][boxLoc[1]] = true;
        while (!q.isEmpty()) {
            int[] currPlLoc = q.poll();
            if (currPlLoc[0] == i && currPlLoc[1] == j) return true;
            for (int[] d : dir) {
                int r = currPlLoc[0] + d[0], c = currPlLoc[1] + d[1];   
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == '#') continue; 
                visited[r][c] = true; 
                q.offer(new int[]{r, c});
            }
        }
        return false;
    }


    

    
}
