package com.ld.graph;

import java.io.File;

public class GraphTest {

    public static void main(String[] args) {
        File directory = new File("");
        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("java.class.path"));
        System.out.println(directory.getAbsolutePath());//获取绝对路径
        System.out.println(directory.getPath()); //获得new File()时设定的路径

        String sysPath = System.getProperty("user.dir");
        Graph graph = new Graph(sysPath + "\\tinyGraph.txt");

        System.out.println(graph);

    }

}
