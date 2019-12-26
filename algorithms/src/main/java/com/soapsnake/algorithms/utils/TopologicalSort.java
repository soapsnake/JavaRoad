package com.soapsnake.algorithms.utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.soapsnake.algorithms.structures.graph.direct.Vertex;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2019-12-25
 */
public class TopologicalSort {
    /**
     *
     * https://blog.csdn.net/oDaiLiDong/article/details/85564366
     *
     * 判断是否有环及拓扑排序结果
     * <p>
     * 有向无环图(DAG)才有拓扑(topological)排序
     * 广度优先遍历的主要做法：
     * 1、遍历图中所有的顶点，将入度为0的顶点入队列。
     * 2、从队列中poll出一个顶点，更新该顶点的邻接点的入度(减1)，如果邻接点的入度减1之后等于0，则将该邻接点入队列。
     * 3、一直执行第2步，直到队列为空。
     * 如果无法遍历完所有的结点，则意味着当前的图不是有向无环图。不存在拓扑排序。
     *
     * @return key返回的是状态, 如果成功(无环)为true, 失败则有环， value为拓扑排序结果(可能是其中一种)
     */
    private Map<Vertex, VertexInfo> verticesMap = new HashMap<>();

    private Map.Entry<Boolean, List<Vertex>> topologicalSort() {
        //入度为0的结点队列
        Queue<Vertex> zeroIndegreeVertexQueue = new LinkedList<>();
        //保存结果
        List<Vertex> topoResultList = new ArrayList<>();
        //保存入度不为0的结点
        Map<Vertex, Integer> notZeroIndegreeVertexMap = new HashMap<>();

        //扫描所有的顶点,将入度为0的顶点入队列
        for (Map.Entry<Vertex, VertexInfo> vertices : verticesMap.entrySet()) {
            Vertex vertex = vertices.getKey();
            int inDegree = getIndegree(vertex);

            if (inDegree == 0) {
                zeroIndegreeVertexQueue.add(vertex);
                topoResultList.add(vertex);
            } else {
                notZeroIndegreeVertexMap.put(vertex, inDegree);
            }
        }

        //扫描完后，没有入度为0的结点，说明有环，直接返回
        if(zeroIndegreeVertexQueue.isEmpty()){
            return new AbstractMap.SimpleEntry(false, topoResultList);
        }

        //采用topology算法, 删除入度为0的结点和它的关联边
        while (!zeroIndegreeVertexQueue.isEmpty()) {
            Vertex v = zeroIndegreeVertexQueue.poll();
            //得到相邻结点
            Set<Vertex> subsequentNodes = getSubsequentNodes(v);

            for (Vertex subsequentVertex : subsequentNodes) {

                Integer degree = notZeroIndegreeVertexMap.get(subsequentVertex);

                if(--degree == 0){
                    topoResultList.add(subsequentVertex);
                    zeroIndegreeVertexQueue.add(subsequentVertex);
                    notZeroIndegreeVertexMap.remove(subsequentVertex);
                }else{
                    notZeroIndegreeVertexMap.put(subsequentVertex, degree);
                }

            }
        }

        //notZeroIndegreeVertexMap如果为空, 表示没有环
        AbstractMap.SimpleEntry resultMap = new AbstractMap.SimpleEntry(notZeroIndegreeVertexMap.size() == 0 , topoResultList);
        return resultMap;
    }

    private Set<Vertex> getSubsequentNodes(Vertex v) {
        return new HashSet<>();
    }

    private int getIndegree(Vertex vertex) {
        return 0;
    }

    public static class VertexInfo {

    }
}
