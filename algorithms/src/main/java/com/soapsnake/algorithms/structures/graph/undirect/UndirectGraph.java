package com.soapsnake.algorithms.structures.graph.undirect;

import com.soapsnake.algorithms.structures.graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 无向图
 */
public class UndirectGraph implements Graph {

    private int V;  //顶点数目

    private int E;  //边数目

    private Set<Integer>[] adjTable;  //邻接表

    public UndirectGraph() {}

    public UndirectGraph(int v) {
        this.V = v;  //顶点个数
        adjTable = (Set<Integer>[]) new Set[V];
        for (int i = 0; i < V; i++) {
            adjTable[i] = new HashSet<>();
        }
    }

    public UndirectGraph(int v, int e) {
        this.V = v;
        this.E = e;
    }

    //计算顶点V的度数
    public static int degree(UndirectGraph undirectGraph, int v) {
        int degree = 0;
        for (int w : undirectGraph.adj(v)) {
            degree++;
        }
        return degree;
    }

    //计算所有顶点的最大度数
    public static int maxDegree(UndirectGraph undirectGraph) {
        int max = 0;
        for (int v = 0; v < undirectGraph.getV(); v++) {
            if (degree(undirectGraph, v) > max) {
                max = degree(undirectGraph, v);
            }
        }
        return max;
    }

    //计算自环的个数
    public static int numberOfSelfLoops(UndirectGraph undirectGraph) {
        int count = 0;
        for (int v = 0; v < undirectGraph.getV(); v++) {
            for (int w : undirectGraph.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    //向图中添加一条v -> w的边
    public void addEdge(int v, int w) {
        if (v >= adjTable.length || w >= adjTable.length) {
            System.out.println("图中不包含该顶点:" + v + " -> " + w);
        }
        adjTable[v].add(w);
        adjTable[w].add(v);
        E++;
    }

    //返回和v相邻的所有顶点
    public Iterable<Integer> adj(int v) {
        return adjTable[v];
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    @Override
    public Graph makeGraphByPath(String path) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int e = 0;
            int v = 0;
            while (true) {
                String str = reader.readLine();
                if (str == null) break;
                String[] intStr = str.split(":");
                if (intStr[0].equals("v")) {
                    v = Integer.parseInt(intStr[1]);
                    this.V = v;  //顶点个数
                    this.adjTable = (Set<Integer>[]) new Set[V];
                    for (int i = 0; i < V; i++) {
                        this.adjTable[i] = new HashSet<>();
                    }
                    continue;
                }
                int vertexIndex = Integer.parseInt(intStr[0]);
                String[] adjPoints = intStr[1].split(" ");
                for (String vertex : adjPoints) {
                    adjTable[vertexIndex].add(Integer.parseInt(vertex));
                    this.E++;
                }
            }
            return this;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
