package com.soapsnake.algorithms.cruel;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-05-08
 * JavaRoad
 */
public class WeeklyContext292 {

    public static void main(String[] args) {
        WeeklyContext292 weeklyContext292 = new WeeklyContext292();

        TreeNode treeNode = TreeNode.makeBinerSearchTree();  //should be 5 but 4
        System.out.println(weeklyContext292.averageOfSubtree(treeNode));
    }

    public String largestGoodInteger(String num) {
        int count = 1;
        char prev = num.charAt(0);
        int maxnum = -1;
        String res = "";
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) == prev) {
                count++;
                if (count >= 3) {
                    int cur = num.charAt(i) - '0';
                    if (cur > maxnum) {
                        maxnum = cur;
                        res  = num.substring(i - 2, i + 1);
                        //System.out.println("res" + res);
                    }
                }
            } else {
                prev = num.charAt(i);
                count = 1;
            }
        }
        return res;
    }

    public int averageOfSubtree(TreeNode root) {
        //读题: root.val = (root.val + child.val) / n(节点数)
        dfs(root);
        return res;
    }
    int res = 0;

    public int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int sum = root.val;
        int nodecount = 1;
        int[] leftsum = dfs(root.left);
        int[] rightsum = dfs(root.right);
        sum += leftsum[0] + rightsum[0];
        nodecount += leftsum[1] + rightsum[1];
        if (sum / nodecount == root.val) {
            res++;
        }
        return new int[] {sum, nodecount};
    }

    public int countTexts(String pressedKeys) {
        //读题:
        long res = 0;
        return (int) res;
    }

    public boolean hasValidPath(char[][] grid) {
        //读题: 1.只会往下或者往右移动. 2. 括号要合法
        Queue<int[]> que = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int cnt = grid[0][0] == '(' ? 1 : -1;
        que.offer(new int[]{0 , 0, cnt});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curCnt =cur[2];
            if (cur[0] == n - 1 && cur[1] == m - 1 && curCnt == 0) return true;
            for (int k = 0;  k < 2; k++) {
                int x = cur[0] + mx[k];
                int y = cur[1] + my[k];
                if (x >= 0 && y >= 0 && x < n && y < m) {
                    int tmp = grid[x][y] == '(' ? 1 : -1;
                    if (curCnt + tmp >= 0) {
                        que.offer(new int[] {x, y, curCnt + tmp});
                    }
                }
            }
        }
        return false;
    }

    private int countCnt(String curStr) {
        int cnt = 0;
        for (int i = 0; i < curStr.length(); i++) {
            if (curStr.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
        }
        return cnt;
    }

    int[] mx = {0, 1};
    int[] my = {1, 0};

    private boolean valid(String curStr) {
        int cnt = 0;
        for (int i = 0; i < curStr.length(); i++) {
            if (curStr.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }


}

class Node {
    int x;
    int y;
    String s;
    public Node(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}
