package com.ld.graph;

import java.util.List;

public interface Search {
    //找到和给定图给定顶点连通的所有顶点
    void search(Graph graph, int s);

    //v是否标记过
    boolean marked(int v);

    //与v连通的顶点总数
    int count(int v);

    //找出连接两点的所有路径
    List<List<Integer>> allPath(int v, int s);
}
