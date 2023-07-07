package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.graph.Graph;
import com.soapsnake.algorithms.structures.graph.direct.DirectWeightGraph;
import com.soapsnake.algorithms.structures.graph.direct.Vertex;
import com.soapsnake.algorithms.structures.graph.undirect.UndirectGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphTester {

    /**
     * 搜索图的两点最短路径
     * @param undirectGraph
     * @return
     */
    public int shortestPath (UndirectGraph undirectGraph, Vertex start, Vertex end ) {
        Graph graph = DirectWeightGraph.makeWeightGraphForTest();
        int [] dist = new int[10];

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        int c = 0;
        for(Map.Entry<Integer, Integer> next : map.get(c).entrySet()) {
next.getKey()
        }

        return 0;
    }

    @Test
    public void testShortestPath() {
        List<String> ls = new ArrayList<>();
        ls.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
