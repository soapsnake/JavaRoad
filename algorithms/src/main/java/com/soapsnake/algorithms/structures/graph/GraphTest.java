package com.soapsnake.algorithms.structures.graph;

import java.util.Arrays;

public class GraphTest {

    public static void main(String[] args) {
        Graph graph = new Graph("graph1");

        System.out.println(graph);

        //给定一些顶点,计算这些顶点之间的总路径,如果这些点有不联通的,应该抛出异常
        GraphSearch search = new DepthFirstSearch(graph, 0);
        System.out.println(search.countTotal(Arrays.asList(0, 1, 2)));  //计算顶点0, 1, 2路径和


        //给点两个点,找出联通这两点的所有路径
        System.out.println(search.allPath(3));  //stackOverFlow


        //给定两个点,找出联通这两点间所有路径中,距离之和最小的路径


        //给定两点,找出联通这两点间所有路径中,距离之和最大的路径


        //给点两点,找出联通这两点间所有路径中,跳数最多的路径


        //给定两点,找出联通这两点间所有路径中,跳数最少的路径
    }

}
