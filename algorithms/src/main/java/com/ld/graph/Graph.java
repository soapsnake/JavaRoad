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

    private int V;  //顶点

    private int E;  //边

    private Set<Integer>[] adjTable;  //邻接表

    Graph(int v){
        this.V = v;
        adjTable = (Set<Integer>[]) new Set[V];
        for (int i=0;i<V;i++){
            adjTable[i] = new HashSet<>();
        }

    }

    Graph(int v, int e){
        this.V = v;
        this.E = e;
    }

    //从输入流中读取一副图
    Graph(String  filePath){
        try {
            InputStream inputStream = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String str = reader.readLine();
                String[] intStr = str.split(",");
                int v = Integer.parseInt(intStr[0]);
                String points = intStr[1];
                String[] point = points.split(" ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向图中添加一条v -> w的边
    public void addEdge(int v, int w){
        if (v >= adjTable.length || w>= adjTable.length){
            System.out.println("图中不包含该顶点:"+ v + " -> " + w);
        }
        adjTable[v].add(w);
        adjTable[w].add(v);
        E++;
    }

    //计算顶点V的度数
    public static int degree(Graph graph, int v){
        int degree = 0;
        for (int w : graph.adj(v)){
            degree++;
        }
        return degree;
    }

    //计算所有顶点的最大度数
    public static int maxDegree(Graph graph){
        int max = 0;
        for (int v=0;v<graph.getV();v++){
            if (degree(graph, v) > max){
                max = degree(graph, v);
            }
        }
        return max;
    }

    //计算自环的个数
    public static int numberOfSelfLoops(Graph graph){
        int count = 0;
        for (int v=0; v<graph.getV();v++){
            for (int w : graph.adj(v)){
                if (v == w){
                    count++;
                }
            }
        }
        return count/2;
    }

    //返回和v相邻的所有顶点
    public Iterable<Integer> adj(int v){
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
        for (int v = 0; v < V; v++)   {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
            return s;
    }
}
