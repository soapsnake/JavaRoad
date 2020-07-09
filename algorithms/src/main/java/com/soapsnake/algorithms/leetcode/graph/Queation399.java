package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created on 2020-07-05
 */
public class Queation399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> m = new HashMap<>();

        //有像图的初始化,有向图有一个问题,一条边既可以是a -> b,也可以是 b -> a
        for (int i = 0; i < values.length; i++) {
            m.putIfAbsent(equations.get(i).get(0), new HashMap<>());  //顶点a
            m.putIfAbsent(equations.get(i).get(1), new HashMap<>());  //顶点b
            m.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);  // a -> b, weight = a / b
            m.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]); //b -> a, weight = b / a
        }

        double[] r = new double[queries.size()];  //结果的数量与queries数量相等
        for (int i = 0; i < queries.size(); i++)
            //r[i] = a -> b
            r[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, m, new HashSet<>());
        return r;
    }

    private double dfs(String s, String t, double r, Map<String, Map<String, Double>> m, Set<String> seen) {
        //递归终止条件,如果有向图不包含顶点s,那么退出,t不用校验了吗?
        //seen缓存用来防止环路的吗?
        if (!m.containsKey(s) || !seen.add(s)) return -1;
        //如果s和t是同一个顶点,这种情况表名s点和t终于联通,也就是从s出发可达t点
        if (s.equals(t)) return r;
        //s的邻居节点是可以有很多个的,用map的好处就体现出来了
        Map<String, Double> next = m.get(s);
        for (String c : next.keySet()) {
            //遍历s的所有邻居节点,注意 r * next.get(c)其实就是 preWeight * curWeight
            double result = dfs(c, t, r * next.get(c), m, seen);
            //如果返回-1说明要么s和t不联通,要么发现了环路
            if (result != -1) return result;
        }
        return -1;
    }








}
