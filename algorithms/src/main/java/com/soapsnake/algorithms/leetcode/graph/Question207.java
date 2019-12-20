package com.soapsnake.algorithms.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Question207 {
    //todo 图的边集表示法弄懂了先: https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs

    /**
     *According to the Wiki about what Topological sorting is (https://en.wikipedia.org/wiki/Topological_sorting)
     * and the Kahn's algorithm as shown below:
     * alt text
     * L ← Empty list that will contain the sorted elements
     * S ← Set of all nodes with no incoming edges
     * while S is non-empty do
     *
     * remove a node n from S
     * add n to tail of L
     * for each node m with an edge e from n to m do
     * remove edge e from the graph
     * if m has no other incoming edges then
     * insert m into S
     * if graph has edges then
     * return error (graph has at least one cycle)
     * else
     * return L (a topologically sorted order)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites){
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] goCourses = new List[numCourses];
        for(int i=0;i<goCourses.length;i++){
            goCourses[i] = new LinkedList<Integer>();
        }
        for(int[] pair: prerequisites){
            incomingEdges[pair[0]]++;
            goCourses[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0;i<incomingEdges.length;i++){
            if(incomingEdges[i]==0){
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int goCrs: goCourses[cur]){
                edgeCnt--;
                if(--incomingEdges[goCrs]==0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt==0;
    }
}
