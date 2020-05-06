package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.graph.Graph;
import com.soapsnake.algorithms.structures.graph.direct.DirectWeightGraph;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-01
 */
public class TopologicalSort {

    Graph graph;

    TopologicalSort() {
        this.graph = DirectWeightGraph.makeWeightGraphForTest();
        System.out.println(graph);
    }

    public void sort() {

    }

    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
    }
}
