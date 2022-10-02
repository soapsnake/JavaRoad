package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-03-20
 * JavaRoad
 */
public class WeeklyContext185 {

    public static int countHillValley(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);
        for (int i = 1 ; i < nums.length; i++ ) {
            if (nums[i] != nums[i - 1]) {
                temp.add(nums[i]);
            }
        }
        int res = 0;
        for (int i = 1; i < temp.size() - 1; i++) {
            if (temp.get(i) > temp.get(i - 1) && temp.get(i) > temp.get(i + 1)) {
                //System.out.println("peek" + i);
                res++;
            } else if (temp.get(i) < temp.get(i - 1) && temp.get(i) < temp.get(i + 1)) {
                //System.out.println("valle" + i);
                res++;
            }
        }
        return res;
    }


    public static int countCollisions(String directions) {
        if (directions.length() == 1) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < directions.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(directions.charAt(i));
            } else {
                char prev = stack.peek();
                char cur = directions.charAt(i);
                if (prev == 'R') {
                    if (cur == 'S') {
                        while(stack.peek() == 'R') {
                            res++;
                            stack.pop();
                        }
                        stack.push('S');
                    } else if (cur == 'L') {
                        res += 2;
                        stack.push('S');
                    }
                } else if(prev == 'L') {
                    stack.push(cur);
                } else if(prev == 'S') {
                    if (cur == 'S') {

                    } else if (cur == 'L') {
                        res++;
                    } else if (cur == 'R') {
                        stack.push(cur);
                    }
                }
            }
        }
        int[][] p = new int[100][100];
        return res;
    }

    public static void main(String[] args) {
        int[] nums= {2,4,1,1,6,5};
        System.out.println(0x3f3f3f3f);
        String str = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";   //应该是20
        System.out.println(countCollisions(str));
    }
}
