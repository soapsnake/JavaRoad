package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soapsnake.algorithms.structures.list.ListNode;

public class WeeklyContext300 {
    public static void main(String[] args) throws Exception {
        WeeklyContext300 week = new WeeklyContext300();

        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        // System.out.println(week.decodeMessage(key, message));
        int m = 3;
        int n = 5;
        //3,0,2,6,8,1,7,9,4,2,5,5,0
        ListNode head = new ListNode(3).next(0).next(2).next(6).next(8).next(1).next(7).next(9).next(4).next(2).next(5).next(5).next(0);
        System.out.println(Arrays.deepToString(week.spiralMatrix(m, n, head)));
    }

    public String decodeMessage(String key, String message) {
        //读题: key每个字符第一次出现的索引,映射成a,b,c....z
        //message 每个字符按照映射替换成新的字符串
        Map<Character, Character> map = new HashMap<>();  // char-> firstIndex
        int i = 0;
        for (char c: key.toCharArray()) {
            if (c == ' ') continue;
            if (!map.containsKey(c)) {
                int index = i % 26;
                char tmp = (char)(index + 'a');
                map.put(c,  tmp);
            } else {
                continue;
            }
            i++;
        }
        // System.out.println(map);
        StringBuilder sb = new StringBuilder();
        for (char c: message.toCharArray()) {
            if (c == ' ') {
                sb.append(" ");
                continue;
            }
            // System.out.println("" + c + " = " + map.get(c));
            sb.append(map.get(c));
        }
        return sb.toString();
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] mt = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(mt[i], -1);
        }
        int i = 0;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = 0, column = 0;
        ListNode cur = head;
        int directionIndex = 0;
        while (cur != null) {
            mt[row][column] =cur.val;
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= m || nextColumn < 0 || nextColumn >= n  || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
            cur = cur.next;
            i++;
        }
        return mt;
    }

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int delayDay = 0;  // day == delay, can distribute
        int fday = 0;
        //day == forget 
        //思路: 如果i - 1天有k个人知道, i 天如果没人忘记那就是 k * 2个人知道
        //prevForget = total
        int total = 1;
        int mod = (int) 1e9+7;
        List<Integer> f = new ArrayList<>();
        for (int i = 1; i  <= n; i++) {
            boolean canShare = true;
            if (delayDay == delay) {
                canShare = false;
                delayDay = 0;
            } else {
                delayDay++;
            }
            if (fday == forget) {
                int count = f.get(f.size() - 1);
                total -= count;
                fday = 0;
            } else {
                fday++;
            }
            if (canShare) {
                total  = total * 2 % mod;
                f.add(total);
            }
        }
        return total;
    }

    int mod = (int)1e9 + 7;
    long[][] memo;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int countPaths(int[][] grid) {
        long res = 0;
        int m = grid.length;
        int n = grid[0].length;
        memo = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = (res + dfs(grid, i, j)) % mod;
            }
        }
        return (int) res;
    }

    private long dfs(int[][] grid, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        long res = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > grid[i][j]) {
                res = (res + dfs(grid, x, y)) % mod;
            }
        }
        return memo[i][j] = res;
    }




}
