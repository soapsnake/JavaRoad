package com.soapsnake.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-25 09:06
 */
public class Question62 {

    /**
     *
     *
     * Example 1:
     * Input: right = 3, down = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down
     * 2. Right -> Down -> Right
     * 3. Down -> Right -> Right
     *
     * Example 2:
     * Input: right = 7, down = 3
     * Output: 28
     */

    //todo 正确解法
    // https://leetcode.com/problems/unique-paths/discuss/220418/Java-solution-with-explanations-in-Chinese
    public int uniquePaths(int m, int n) {
        this.right = m;
        this.down = n;
        List<String> paths = new ArrayList<>();
        dfs(1, 1 , paths, "");
        System.out.println(paths);
        return paths.size();
    }
    int right = 0;
    int down = 0;
    private void dfs(int m, int n, List<String> paths, String path) {
        if (m == this.right && n == this.down) {
            paths.add(path);
            return;
        }
        System.out.println("m = " + m + " n = " + n);
        System.out.println("size = " + paths.size());
        if (m < this.right) {
            dfs(m + 1, n, paths, path + "right->");
        }
        if (n < this.down) {
            dfs(m, n + 1, paths, path + "down->");
        }
    }

    public int uniquePathsSample(int m, int n) {
        int[] dp = new int[m];
        dp[0] = 1;
        for (int i = 0; i < n; i++)
            for (int j = 1; j < m; j++)
                dp[j] = dp[j - 1] + dp[j];
        return dp[m - 1];
    }

    public static void main(String[] args) {
        Question62 question62 = new Question62();
        int m = 23;
        int n = 12;
        System.out.println(question62.uniquePathsSample(m, n));
    }
}
