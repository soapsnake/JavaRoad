package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-12-25
 * JavaRoad
 */
public class WeeklyContext325 {

    public static void main(String[] args) {
        WeeklyContext325 w = new WeeklyContext325();
        int[] coins = {8, 10};
        int k = 6;
        System.out.println(w.findKthSmallest(coins, k));
    }

    public String getSmallestString(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            int dist1 = Math.abs(c - 'a');
            int dist2 = Math.abs('z' - c);
            int dist = Math.min(dist1, dist2);
            if(dist > k) {
                char d = Character.isLetter((c + k)) ? (char) (c + k) : (char) (c - k);
                sb.append(d);
                break;
            }
            sb.append('a');
            k -= dist;
        }
        return sb.toString();
    }

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        //数据先排序，第0个数乘k，如果乘k后比第1个数大，说明肯定覆盖了，
        Arrays.sort(coins);
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            arr.add(coins[0] * (i + 1));
        }
        for(int i = 1; i < coins.length; i++) {
            int cur = coins[i];
            if(cur >= arr.get(k - 1)) {
                break;
            }
            int b = 1;
            System.out.println(arr);
            int ha = cur;
            while(ha < arr.get(k - 1)) {
                ha = cur * b;
                //System.out.println("cur = " + ha + " b = " + b);
                int index = find(arr, ha);
                if(index < k && arr.get(index) != ha) {
                    arr.add(index, ha);
                    arr.remove(arr.size() - 1);
                }
                b++;
            }
        }
        //System.out.println(arr);
        return arr.get(arr.size() - 1);
    }

    private static int find(List<Integer> num, int k) {
        int left = 0, right = num.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(num.get(mid)< k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
