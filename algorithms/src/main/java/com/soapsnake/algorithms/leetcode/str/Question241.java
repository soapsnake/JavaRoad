package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question241 {

    public static void main(String[] args) {
        Question241 question241 = new Question241();
        String str = "11*12";
        System.out.println(question241.diffWaysToCompute(str));  //shoud be [-34, -14, -10, -10, 10]
    }


    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if(map.containsKey(input)) return map.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+' ) {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                System.out.println("part1Ret = " + part1Ret);
                System.out.println("part2Ret = " + part2Ret);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        res.add(c);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }

    public List<Integer> diffWaysToCompute2(String input) {
        List<Integer> result = new ArrayList<>();
        if (input == null || input.length() == 0) return result;
        List<String> ops = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {   //他这个写法有点绕,其实就是把数字和操作符切分开, 比如1+1会切分成[1,+,1]
            //为什么不能用toCharArray,是因为11*11这种需要切成[11,*,11]而不是[1,1,*,1,1]
            int j = i;
            while (j < input.length() && Character.isDigit(input.charAt(j)))
                j++;
            String num = input.substring(i, j);
            ops.add(num);
            if (j != input.length()) ops.add(input.substring(j, j + 1));
            i = j;
        }
        System.out.println("ops is = " + ops);
        result = compute(ops, 0, ops.size() - 1);
        return result;
    }

    private List<Integer> compute(List<String> ops, int lo, int hi) {
        List<Integer> result = new ArrayList<>();
        //左右指针
        if (lo == hi) {  //这里为什么lo指针一定是指向数字了?????
            Integer num = Integer.valueOf(ops.get(lo));
            result.add(num);
            return result;
        }
        for (int i = lo + 1; i <= hi - 1; i = i + 2) {
            String operator = ops.get(i);   //奇数位一定是操作符,偶数位一定是数字,lo + 1 = i 所以lo一定是数字
            List<Integer> left = compute(ops, lo, i - 1);
            List<Integer> right = compute(ops, i + 1, hi);
            for (int leftNum : left)
                for (int rightNum : right) {
                    if (operator.equals("+"))
                        result.add(leftNum + rightNum);
                    else if (operator.equals("-"))
                        result.add(leftNum - rightNum);
                    else
                        result.add(leftNum * rightNum);
                }
        }
        return result;
    }
}
