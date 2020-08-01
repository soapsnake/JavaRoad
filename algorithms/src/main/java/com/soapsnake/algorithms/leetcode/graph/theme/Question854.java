package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Created on 2020-07-27
 */
public class Question854 {

    //leetocde854: A中的字符经过最少多少次的交换,可以变成B
    public int kSimilarity(String A, String B) {
        if (A.equals(B))
            return 0;
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(A);
        vis.add(A);
        int count = 0;
        //图的bfs算法
        while (!q.isEmpty()) {
            count++;
            for (int sz = q.size(); sz > 0; sz--) {
                String s = q.poll();
                int i = 0;
                while (s.charAt(i) == B.charAt(i))
                    i++;

                //到此,i指向的字符是AB不一样的,可能需要进行交换
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == B.charAt(j) || s.charAt(j) != B.charAt(i))
                        //1.如果j所指的字符,和B中该字符相同,不需要交换
                        //2.如果j所指字符,和B中的i处字符(循环初始位字符)不一样,也不需要交换(为啥???)
                        continue;

                    //到这里说明:i处字符,AB不一样,j处字符,AB也是不一样的,(这样交换后才有一样的可能)
                    //举个例子: A = abcdef
                    //        B = abedcf
                    //i = 2 处和 i = 4处字符都不一样,但是把这俩交换后就是一致的
                    String temp = swap(s, i, j);
                    if (temp.equals(B))
                        //A和B已经一致了,没有必要继续运算下去了
                        return count;

                    //到这里说明换完一对字符后还是不相等,把换完后的字串A塞回去再来一轮
                    if (vis.add(temp))
                        q.add(temp);
                }
            }
        }
        return count;
    }

    public String swap(String s, int i, int j) {
        char[] ca = s.toCharArray();
        char temp = ca[i];
        ca[i] = ca[j];
        ca[j] = temp;
        return new String(ca);
    }
}
