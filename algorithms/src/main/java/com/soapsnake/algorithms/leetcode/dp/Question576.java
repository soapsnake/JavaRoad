package com.soapsnake.algorithms.leetcode.dp;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *E
 * Created on 2020-05-06
 */
public class Question576 {

    //leetcode576
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) return 0;
        final int MOD = 1000000007;
        int[][] count = new int[m][n];
        count[i][j] = 1;
        int result = 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int step = 0; step < N; step++) {
            int[][] temp = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                            result = (result + count[r][c]) % MOD;
                        }
                        else {
                            temp[nr][nc] = (temp[nr][nc] + count[r][c]) % MOD;
                        }
                    }
                }
            }
            count = temp;
        }
        return result;
    }

    //判断二叉树两节点是否表兄节点:处于树的同一层但是父节点不相同
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();  //  node -> parent
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<TreeNode> row = new ArrayList<>();
            List<Integer> rowNum = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    map.put(node.left.val, node.val);
                    row.add(node.left);
                    rowNum.add(node.left.val);
                }
                if (node.right != null) {
                    map.put(node.right.val, node.val);
                    row.add(node.right);
                    rowNum.add(node.right.val);
                }
            }
            if (row.size() != 0) {
                res.add(rowNum);
                queue.addAll(row);
            }
        }
        for (List<Integer> list : res) {
            if (list.contains(x) && list.contains(y)) {
                return map.get(x) != map.get(y);
            }
        }
        return false;
    }

}
