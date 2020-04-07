package com.soapsnake.algorithms.leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * Created on 2020-03-20
 */
public class Question787 {

    /**
     * Example 2:
     * Input:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * Output: 500
     * Explanation:
     * The graph looks like this:
     * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
     */
    //leetcode 787
    private class City implements Comparable<City>{
        int id;
        int costFromSrc;
        int stopFromSrc;
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
        public boolean equals(City c){
            if(c != null)
                return this.id == c.id;
            return false;
        }
        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for (int[] flight : flights) {
            srcToDst[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<City> minHeap = new PriorityQueue<>();
        minHeap.offer(new City(src,0,0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }
        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }
}
