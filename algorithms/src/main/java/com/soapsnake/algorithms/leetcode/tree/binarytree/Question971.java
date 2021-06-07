package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * Created on 2021-03-30
 */
public class Question971 {

    List<Integer> res = new ArrayList<>();
    int i = 0;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return dfs(root, voyage) ? res : Arrays.asList(-1);
    }

    public Boolean dfs(TreeNode node, int[] v) {
        if (node == null) return true;
        if (node.val != v[i++]) {
            return false;
        }
        //到这里说明node.val == v[i]
        //到这里i已经++
        if (node.left != null && node.left.val != v[i]) {
            res.add(node.val);
            return dfs(node.right, v) && dfs(node.left, v);
        }
        return dfs(node.left, v) && dfs(node.right, v);
    }

    //其实是数组最长递增子序列问题,具体到这个题目是题目是两个维度都必须递增
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return n;
        // 因为我们在找第 i 件物品的前一件物品时，会对前面的 i - 1 件物品都遍历一遍，因此第二维（高度）排序与否都不影响
        Arrays.sort(envelopes, (a, b)->a[0]-b[0]);  //对宽度进行递增排序
        int[] dp = new int[n]; // f(i) 为考虑前 i 个物品，并以第 i 个物品为结尾的最大值
        int res = 1;
        for (int i = 0; i < n; i++) {
            // 对于每个 f[i] 都满足最小值为 1
            dp[i] = 1;
            // 枚举第 i 件物品的前一件物品，
            for (int j = i - 1; j >= 0; j--) {
                // 只要有满足条件的前一件物品，我们就尝试使用 f[j] + 1 更新 f[i]
                if (check(envelopes, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);  //状态转移方程
                }
            }
            // 在所有的 f[i] 中取 max 作为 ans
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    boolean check(int[][] es, int mid, int i) {
        return es[mid][0] < es[i][0] && es[mid][1] < es[i][1];
    }

    public static void main(String[] args) {
        Question971 question971 = new Question971();
        int[][] env = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(question971.maxEnvelopes(env));
    }
}
