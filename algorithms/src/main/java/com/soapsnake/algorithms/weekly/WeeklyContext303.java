package com.soapsnake.algorithms.weekly;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-07-24
 * JavaRoad
 */
public class WeeklyContext303 {

    public static void main(String[] args) {
        WeeklyContext303 weeklyContext303 = new WeeklyContext303();
        System.out.println("jfidas");

        //int[][] grid = {{3,2,1},{1,7,6},{2,7,7}};
        //System.out.println(weeklyContext303.equalPairs(grid));

        int[] arr = {1,2,3,1};
        int k = 3;
        System.out.println(weeklyContext303.countExcellentPairs(arr, k));
        int[] nums = {1};
        int[] nums1 = Arrays.stream(nums).map(o -> -o + 1000).toArray();


    }

    public int equalPairs(int[][] grid) {
        //读题: 一行和一列完全相同(顺序!!!!), 要返回的是数目
        //思路: 列全部转成行,然后平方级比对,5次方,应该可以
        int m = grid.length;
        int n = grid.length;
        int[][] grid2 = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j= 0 ; j < n; j++) {
                grid2[j][i] = grid[i][j];
            }
        }
        System.out.println(Arrays.deepToString(grid2));
        System.out.println(Arrays.deepToString(grid));
        int res = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < m; j++) {
                if (Arrays.equals(ints, grid2[j])) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean dengyu(int[] ints, int[] ints1) {
        int n = ints.length;
        for (int i = 0; i < n; i++) {
            if (ints[i] != ints1[1]) {
                return false;
            }
        }
        return true;
    }

    public long countExcellentPairs2(int[] nums, int k) {
       Arrays.sort(Arrays.stream(nums).mapToObj(Integer::toBinaryString).toArray(), Comparator.comparingInt(a -> count(a.toString())));
       int res = 0;
       int remain = 0;
       int sigle = 0;
       for (int i = 0; i < nums.length; i++) {
           int m = count(Integer.toBinaryString(nums[i]));
           if (m < k / 2) {

           } else if (m == k / 2) {
               sigle++;
           } else if (m >= k) {
               remain++;
           }
       }
       return (long) remain * nums.length + sigle;
    }
    private static int count(String ands) {
        int res = 0;
        for(int i = 0; i < ands.length(); i++) {
            if (ands.charAt(i) == '1') {
                res++;
            }
        }
        return res;
    }

        public long countExcellentPairs(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (valid(nums[i], nums[j], k)) {
                    System.out.println(nums[i] + "--->" + nums[j]);
                    if (set.add(nums[i] + "--->" + nums[j])) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static boolean valid(int a, int b, int k) {
        int and = a & b;
        int or = a | b;
        String ands = Integer.toBinaryString(and);
        String ors = Integer.toBinaryString(or);
        return count(ands) + count(ors) >= k;
    }


}

class FoodRatings {

    Map<String, Queue<FoodNode>> map = new HashMap<>();   //cuisine -> foods
    Map<String, String> foodMap = new HashMap<>();  //food -> cuisine
    Map<String, FoodNode> nodeMap = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i = 0 ; i < n; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rate = ratings[i];
            FoodNode foodNode = new FoodNode(food, rate);
            foodMap.put(food, cuisine);
            nodeMap.put(food, foodNode);
            map.computeIfAbsent(cuisine, x -> new PriorityQueue<>((a, b) -> {
                if (a.rate != b.rate) {
                    return b.rate - a.rate;
                } else {
                    return a.food.compareTo(b.food);
                }
            })).offer(foodNode);
        }
    }

    //复杂度太高了
    public void changeRating(String food, int newRating) {
        String cuisine = foodMap.get(food);
        Queue<FoodNode> que = map.get(cuisine);
        FoodNode node = nodeMap.get(food);
        FoodNode newNode =new FoodNode(food, newRating);
        nodeMap.put(food, newNode);
        Integer.bitCount(newRating);
        que.remove(node);
        que.offer(newNode);
    }

    //返回指定烹饪方式 cuisine 下评分最高的食物的名字。如果存在并列，返回 字典序较小 的名字。
    public String highestRated(String cuisine) {
        Queue<FoodNode> que = map.get(cuisine);
        return que.peek().food;
    }

}
class FoodNode {
    String food;
    int rate;
    public FoodNode(String food, int rate) {
        this.food = food;
        this.rate = rate;
    }
}
