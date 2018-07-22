package com.ld.graph;

import java.util.List;
import java.util.Stack;

public interface Search {
    //s -> v是否连通
    boolean hasPathTo(int v);

    //与v连通的顶点总数
    int count(int v);

    //找出连接s -> v的所有路径
    List<List<Integer>> allPath(int s);

    //找出s -> v的一条路径
    Stack<Integer> pathTo(int v);
}
