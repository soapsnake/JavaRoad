package com.ld.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 无向图API
 */
public class Graph {

    private int V;  //顶点数目

    private int E;  //边数目

    private Set<Integer>[] adjTable;  //邻接表

    Graph(int v) {
        this.V = v;  //顶点个数
        adjTable = (Set<Integer>[]) new Set[V];
        for (int i = 0; i < V; i++) {
            adjTable[i] = new HashSet<>();
        }
    }

    Graph(int v, int e) {
        this.V = v;
        this.E = e;
    }

    //从输入流"tinyGraph.txt"中读取一副图
    Graph(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
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
                    adjTable = (Set<Integer>[]) new Set[V];
                    for (int i = 0; i < V; i++) {
                        adjTable[i] = new HashSet<>();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //计算顶点V的度数
    public static int degree(Graph graph, int v) {
        int degree = 0;
        for (int w : graph.adj(v)) {
            degree++;
        }
        return degree;
    }

    //计算所有顶点的最大度数
    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.getV(); v++) {
            if (degree(graph, v) > max) {
                max = degree(graph, v);
            }
        }
        return max;
    }

    //计算自环的个数
    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.getV(); v++) {
            for (int w : graph.adj(v)) {
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
}
