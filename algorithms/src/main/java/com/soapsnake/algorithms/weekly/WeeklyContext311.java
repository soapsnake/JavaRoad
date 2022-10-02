package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.tree.TreeNode;
import com.sun.tools.corba.se.idl.constExpr.And;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-09-18
 * JavaRoad
 */
public class WeeklyContext311 {

    public static void main(String[] args) {

    }

    public TreeNode reverseOddLevels(TreeNode root) {
        //翻转奇数层, root节点是0层,偶数
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int level = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            Stack<Integer> layer = new Stack<>();
            List<TreeNode> layerNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = que.poll();
                layerNodes.add(cur);
                if (cur != null) {
                    layer.push(cur.val);
                }
                if (cur.left != null) {
                    que.offer(cur.left);
                }
                if (cur.right != null) {
                    que.offer(cur.right);
                }
            }
            if (level % 2 == 1) {
                for (TreeNode node : layerNodes) {
                    node.val = layer.pop();
                }
            }
            level++;
        }
        return root;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inde = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            inde[b]++;
        }
        Queue<Integer> que = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inde[i] == 0) {
                cnt++;
                que.offer(i);
            }
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                inde[next]--;
                if (inde[next] == 0) {
                    cnt++;
                    que.offer(next);
                }
            }
        }
        return cnt == numCourses;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inde = new int[numCourses];
        for (int[] p : prerequisites) {
            int a = p[0];
            int b = p[1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            inde[b]++;
        }
        Queue<Integer> que = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inde[i] == 0 ){
                que.offer(i);
                cnt++;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!que.isEmpty()) {
            int cur = que.poll();
            res.add(cur);
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                inde[next]--;
                if (inde[next] == 0) {
                    que.offer(next);
                    cnt++;
                }
            }
        }
        if (cnt < numCourses) {
            return new int[0];
        }
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[numCourses - 1 - i] = res.get(i);
        }
        return ans;
    }

}
