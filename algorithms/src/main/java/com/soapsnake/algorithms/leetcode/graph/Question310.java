package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question310 {

    /**
     * @param n  节点个数
     * @param edges   邻接表,每一个[][]代表一条无向边(无向图)
     * @return
     *
     * 算法的整体思路,其实是转圈剪除叶子节点,一轮一轮清除外围的叶子节点,最后剩下来的就是根节点,
     * 可以把root理解成花的花心,外面包围着一层层的花瓣,当把外围的花瓣一层一层剥掉后,最后剩下的就是花心了
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        List<Set<Integer>> adj = new ArrayList<>(n);   //索引是顶点,set中的数字为该顶点可以到达的另一个顶点
        for (int i = 0; i < n; ++i) {
            adj.add(new HashSet<>());
        }
        //{{1,2} , {2,3} ,{3,4}}
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);     //无向图, 1->2和2->1其实是同样的一条边
        }

        List<Integer> leaves = new ArrayList<>();   //所有的叶子节点
        for (int i = 0; i < n; ++i) {
            if (adj.get(i).size() == 1) {  //如果和一个顶点相连的顶点只有一个,那么这个顶点其实是一个叶子节点
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();   //把所有的叶子节点去掉
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();  //j就是上面叶子节点所指向的节点,注意,叶子节点只指向一个节点
                adj.get(j).remove(i);  //删除j -> i,其实相当于移除了叶子节点
                if (adj.get(j).size() == 1) {
                    newLeaves.add(j);   //如果j删掉了指向i的边后,出度只剩下1,表名j此时也变成了一个叶子节点
                }
            }
            leaves = newLeaves;   //旧的叶子节点已经被剪出干净,循环进行下一轮剪叶子吧!!
        }
        return leaves;
    }

    //leetcode1283
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = (int)1e6;
        while (left < right) {
            int m = (left + right) / 2, sum = 0;
            for (int i : nums)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }
}
