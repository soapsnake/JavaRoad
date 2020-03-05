package com.soapsnake.algorithms.structures.graph.direct;

public class Edge {

    //有向边起点
    Vertex start;
    //有向边终点
    Vertex end;
    //边长
    int distance;

    String desc;

    public Edge(Vertex start, Vertex end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.desc = start.getState() + "->" + end.getState();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", desc='" + desc + '\'' +
                '}';
    }
}
