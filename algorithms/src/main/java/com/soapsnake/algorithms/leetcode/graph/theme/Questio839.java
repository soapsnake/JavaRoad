package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.LinkedList;
import java.util.Queue;

/**
 * }
 * Created on 2020-07-16
 */
public class Questio839 {

    //对A进行分组,每个分组内的单词都是异构次(交换字符后相同),问A中有多少个这样的组
    //bfs算法
    public int numSimilarGroups(String[] A) {
        if( A == null || A.length == 0) {
            return 0;
        }
        boolean[] visited = new boolean[A.length];
        Queue<String> queue = new LinkedList<>();

        int ans = 0;

        //bfs算法就很好理解了,相当于联通性,能够在一次A[i]中被访问到的都是被联通的异构词(也可以理解成传染)
        for (int i = 0; i < A.length; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            ans++;  //被访问过的单词,会被标记visited,就会跳过
            queue.add(A[i]);
            while(!queue.isEmpty()) {
                String sameGroup = queue.poll();
                for (int j = 0; j < A.length; j++) {
                    if(visited[j]) {
                        continue;
                    }
                    int diff = 0;
                    boolean same = false;
                    for (int l = 0; l < A[j].length(); l++) {
                        //rats -> tars  => diff = 2   异构词只需要交换两个字符一次就可以相等,意味着diff=2可直接判定成异构词
                        //tars -> star  => diff = 4  这个超过了2就不再构成异构词了
                        if (sameGroup.equals(A[j])) {
                            same = true;
                            break;
                        }
                        if(sameGroup.charAt(l) != A[j].charAt(l)) {
                            diff++;
                        }
                    }
                    if(same || diff == 2) {
                        visited[j] = true;
                        queue.add(A[j]);
                    }
                }
            }

        }
        return ans;
    }

    //dfs算法,这个算法比bfs要快10倍以上!!
    public int numSimilarGroups2(String[] A) {
        //边界条件
        if (A.length < 2)
            return A.length;
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null)
                continue;
            String str = A[i];
            //这里把A给清空了
            A[i] = null;
            res++;
            dfs(A, str);
        }
        return res;
    }

    public void dfs(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null)
                continue;
            if (helper(str, arr[i])) {// both string str and arr[i] belong in same group
                String s = arr[i];
                arr[i] = null;
                dfs(arr, s);  //这个dfs我是完全没看出来和图的dfs有什么关系
            }
        }
    }

    public boolean helper(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        int res = 0, i = 0;
        while (res <= 2 && i < s.length()) {
            if (s.charAt(i) != t.charAt(i))
                //如果对应i处字符不同就递增res,直到res超过2个,什么意思?字符不一样就代表不是异构了吗?那tap和pat岂不不符合要求???
                res++;
            i++;
        }
        return res == 2;
    }
}
