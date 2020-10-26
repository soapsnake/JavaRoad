package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question456 {


    //https://leetcode.com/problems/132-pattern/discuss/94077/Java-O(n)-solution-using-stack-in-detail-explanation
    class Pair{
        int min, max;
        public Pair(int min, int max){
            this.min = min;
            this.max = max;
        }
    }
    //leetcode456
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int k = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < k)
                return true;
            //j, 1 3 2
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                k = stack.pop();
            }
            stack.push(nums[i]); //push j, k
        }
        return false;
    }

    //leetcode456
    public boolean find132pattern2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        List< int[] > intervals = new ArrayList< >();
        int i = 1, s = 0;
        while (i < nums.length) {
            if (nums[i] <= nums[i - 1]) {
                if (s < i - 1)
                    intervals.add(new int[] {nums[s], nums[i - 1]});
                s = i;
            }
            for (int[] a: intervals)
                if (nums[i] > a[0] && nums[i] < a[1])
                    return true;
            i++;
        }
        return false;
    }
}
