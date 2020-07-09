package com.soapsnake.algorithms.leetcode.graph;

import java.util.Arrays;

import org.junit.Test;

import com.soapsnake.algorithms.structures.unionfind.DSU;

/**
 *
 * Created on 2020-07-07
 */
public class Question685 {

    //leetcode685,有向图去掉边变成树
    public int[] findRedundantDirectedConnection(int[][] edges) {
        DSU dsu = new DSU(1024);
        int[] father = new int[edges.length + 1];     //祖先节点记录,记录的是每个节点的父亲节点(只到父亲)
        int[] edge1 = null;
        int[] edge2 = null;
        int[] lastEdgeCauseCircle = null;
        for (int[] pair : edges) {
            int from = pair[0];
            int to = pair[1];
            if (father[to] != 0) {
                // node to already has a father, so we just skip the union of this edge and check if there will be a circle ，跳过 edge2,并记下 edge1,edge2
                //到这里说明一个节点有两个入度,说明探测到了环了吗???
                edge1 = new int[] {father[to], to};  //上一个指向to的边
                edge2 = pair;                        //一条新的指向to的边
            } else {
                father[to] = from;
                if (!dsu.union(from, to)) {
                    //from和to之前有联通过,这里又一次联通,说明探测到了环路,这里用一个变量保存了这个造成环路的边
                    lastEdgeCauseCircle = pair;
                }
            }
        }

        //情况1:如果edge1不为null,那就说明侦测到了一个有两个入度的节点,edge1和edge2就是分别指向一个顶点的两条边
        if (edge1 != null && edge2 != null)
            return lastEdgeCauseCircle == null ? edge2 : edge1; //如果是情况2、3，则根据有没有碰到环返回 edge1 或 edge2
        else
         //情况2 :如果没有发现有两个入度的节点,那其实就是root节点被其他节点给指了,其实就是出现了环路,那么造成环路的边就是有问题的边,
            //这种情况类似于leetcode684
            return lastEdgeCauseCircle;
    }

    @Test
    public void test() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }
}
