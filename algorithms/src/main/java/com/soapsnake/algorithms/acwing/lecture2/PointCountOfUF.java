package com.soapsnake.algorithms.acwing.lecture2;
import java.util.*;

/**
 * Created on 2023-06-14
 */
public class PointCountOfUF {

    /**
     * 连通块中点的数量
     *
     * 给定一个包含 n个点（编号为 1∼n）的无向图，初始时图中没有边。
     * 现在要进行 m个操作，操作共有三种：
     *
     * C a b，在点 a 和点 b 之间连一条边，a 和 b可能相等；
     * Q1 a b，询问点 a 和点 b是否在同一个连通块中，a 和 b可能相等；
     * Q2 a，询问点 a 所在连通块中点的数量；
     * 输入格式
     * 第一行输入整数 n 和 m
     * 接下来 m 行，每行包含一个操作指令，指令为 C a b，Q1 a b 或 Q2 a 中的一种。
     *
     * 输出格式
     * 对于每个询问指令 Q1 a b，如果 a 和 b 在同一个连通块中，则输出 Yes，否则输出 No。
     *
     * 对于每个询问指令 Q2 a，输出一个整数表示点 a 所在连通块中点的数量
     *
     * 每个结果占一行。
     *
     * 数据范围
     * 1≤n,m≤105
     * 输入样例：
     * 5 5
     * C 1 2
     * Q1 1 2
     * Q2 1
     * C 2 5
     * Q2 5
     * 输出样例：
     * Yes
     * 2
     * 3
     */

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n = sc.nextInt();

    }
}
