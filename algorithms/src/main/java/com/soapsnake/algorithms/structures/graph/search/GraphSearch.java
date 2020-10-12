package com.soapsnake.algorithms.structures.graph.search;

import java.util.List;

public interface GraphSearch {
    //s -> v是否连通
    boolean hasPathTo(int v);

    //与v连通的顶点总数
    int count(int v);

    //找出连接s -> v的所有路径
    List<List<Integer>> allPath(int s);

    //找出s -> v的一条路径
    Iterable<Integer> pathTo(int v);

    int countTotal(List<Integer> vertexs);
}
