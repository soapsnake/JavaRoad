package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WeeklyContext283 {




    public List<String> cellsInRange(String s) {
        List<String>  res  = new ArrayList<>();
        String[] temp  = s.split(":");
        char start = temp[0].charAt(0);
        char end = temp[1].charAt(0);
        int startRow = temp[0].charAt(1) - '0';
        int endRow = temp[1].charAt(1) - '0';

        int i = 0;
        while (start + i <= end) {
            char cur = (char) (start + i);
            String curstr = "";
            for (int j = startRow; j <= endRow; j++) {
                curstr = String.valueOf(cur) + j;
                res.add(curstr);
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        WeeklyContext283 weeklyContext283 = new WeeklyContext283();
        int[] nums = {1,4,25,10,25};
        int k = 2;
        //System.out.println(weeklyContext283.minimalKSum(nums, k));

        int o = 3;
        System.out.println("fdjaisfjdsa " + o / 3);
        System.out.println("fdjaisfjdsa " + o % 3);


        int a = 10;
        int b = 3;
        String s = "";
        System.out.println(a % b);
        System.out.println(a / b);

        Scanner sc = new Scanner(System.in);

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(null);
        que.removeFirst();
        que.getLast();
        que.getFirst();
    }

    public long minimalKSum(int[] nums, int k) {
        //from 1 start, pluse 1, if that number exist,then drop

        Arrays.sort(nums);
        long sum = 0L;
        int cnt = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            int prev;
            if (i == 0) {
                prev = 0;
            } else {
                prev = nums[i - 1];
            }
            int curTotal = nums[i] - 1 - (prev  + 1) + 1;
            if (cnt + curTotal >= k) {
                cur = prev + (k - cnt) + 1;
                cnt = k;
                System.out.println("prev = " + prev + " cur = " + cur);
                sum += (long) curTotal * ((prev + 1) + (cur - 1)) / 2;
                break;
            } else {
                cur = nums[i];
                cnt += curTotal;
                System.out.println("prev = " + prev + " cur = " + cur);
                sum += (long) curTotal * ((prev + 1) + (cur - 1)) / 2;
            }
        }
        if (cnt < k) {
            System.out.println("last" + cur);
            sum += (long) (k - cnt) * (cur + 1  + cur + (k - cnt)) /2;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        }
        return sum;   //3444
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> map = new HashMap<>();

        for (Map.Entry<Integer, TreeNode> num : map.entrySet()) {
            num.getKey();
        }
        int n = descriptions.length;
        Set<Integer> pSet = new HashSet<>();
        Set<Integer> cSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int pIdx = descriptions[i][0], cIdx = descriptions[i][1];
            TreeNode p = map.get(pIdx);
            if (p == null) {
                p = new TreeNode(pIdx);
                map.put(pIdx,p);
            }
            TreeNode c = map.get(cIdx);
            if (c == null) {
                c = new TreeNode(cIdx);
                map.put(cIdx,c);
            }
            if (descriptions[i][2] == 1){
                p.left = c;
            } else {
                p.right = c;
            }
            pSet.add(pIdx);
            cSet.add(cIdx);
        }
        pSet.removeAll(cSet);
        for(Integer i : pSet){
            return map.get(i);
        }
        return null;
    }


}
