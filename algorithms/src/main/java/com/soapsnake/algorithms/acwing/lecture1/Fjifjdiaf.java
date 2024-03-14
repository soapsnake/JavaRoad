package com.soapsnake.algorithms.acwing.lecture1;
import java.util.*;

/**
 * Created on 2023-08-09
 */
public class Fjifjdiaf {

    private static Scanner sc =new Scanner(System.in);
    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    private static int INF = 0x3f3f3f3f;
    private static int n;
    public static void main(String[] args) throws Exception {
        n = sc.nextInt();
        int m = sc.nextInt();
        for(int i =0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            Map<Integer, Integer> map = graph.get(a);
            if(map == null) {
                map = new HashMap<>();
            }
            map.put(b, w);
            graph.put(a, map);
        }
        int res= dijkstra(1);
        if(res == INF) System.out.println("-1");
        else System.out.println(res);

        int[][] dege = new int[28][3];
        System.out.println(Arrays.deepToString(dege));
    }

    private static int dijkstra(int src) {
        Queue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        que.offer(new int[]{src, 0});
        Map<Integer, Integer> res = new HashMap<>();
        res.put(src, 0);
        while(!que.isEmpty()) {
            int size = que.size();
            for(int k= 0; k < size; k++) {
                int[] cur = que.poll();
                int a = cur[0];
                for(Map.Entry<Integer, Integer> entry : graph.getOrDefault(a, new HashMap<>()).entrySet()) {
                    int b = entry.getKey();
                    int w = entry.getValue();
                    if(!res.containsKey(b)) {
                        que.offer(new int[] {b, w});
                        res.put(b, cur[1] + w);
                    }
                }
            }
        }
        return res.get(n) == null ? INF : res.get(n);
    }
}
