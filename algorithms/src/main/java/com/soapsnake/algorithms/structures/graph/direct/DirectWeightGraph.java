package com.soapsnake.algorithms.structures.graph.direct;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.soapsnake.algorithms.structures.graph.Graph;

/**
 * 加权有向图,也可以用来表示加权无向图吧
 */
public class DirectWeightGraph implements Graph {

    private Set<Edge>[] adjTable;   //有向图邻接表bag中不再是顶点,而是边,那么数组的索引还是顶点

    public DirectWeightGraph() {

    }

    public static Graph makeWeightGraphForTest() {
        DirectWeightGraph graph = new DirectWeightGraph();
        graph.adjTable = (Set<Edge>[]) new Set[5];
        Vertex A = Vertex.getByChar('A');
        Vertex B = Vertex.getByChar('B');
        Vertex C = Vertex.getByChar('C');
        Vertex D = Vertex.getByChar('D');
        Vertex E = Vertex.getByChar('E');

        Edge AB = new Edge(A, B, 5);
        Edge AE = new Edge(A, E, 7);
        Edge AD = new Edge(A, D, 5);
        Edge BC = new Edge(B, C, 4);
        Edge CE = new Edge(C, E, 2);
        Edge DC = new Edge(D, C, 8);
        Edge CD = new Edge(C, D, 8);
        Edge EB = new Edge(E, B, 3);
        Edge DE = new Edge(D, E, 6);

        Set<Edge> set0 = Stream.of(AB, AE, AD).collect(Collectors.toSet());
        Set<Edge> set1 = Stream.of(BC).collect(Collectors.toSet());
        Set<Edge> set2 = Stream.of(CD, CE).collect(Collectors.toSet());
        Set<Edge> set3 = Stream.of(DC, DE).collect(Collectors.toSet());
        Set<Edge> set4 = Stream.of(EB).collect(Collectors.toSet());

        graph.adjTable[0] = set0;
        graph.adjTable[1] = set1;
        graph.adjTable[2] = set2;
        graph.adjTable[3] = set3;
        graph.adjTable[4] = set4;
        return graph;
    }

    @Override
    public Graph makeGraphByPath(String path) {
        return null;
    }

    @Override
    public String toString() {
        return "DirectWeightGraph{" +
                "adjTable=" + Arrays.toString(adjTable) +
                '}';
    }

    public static void main(String[] args) {
        Graph graph = makeWeightGraphForTest();
        System.out.println(graph);
    }
}
