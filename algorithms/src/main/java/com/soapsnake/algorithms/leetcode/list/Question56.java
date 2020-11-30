package com.soapsnake.algorithms.leetcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-23 22:42
 */
public class Question56 {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Question56 question56 = new Question56();
        List<Interval> intervas = Arrays.asList(new Interval(1, 4),
                new Interval(0, 4));
        System.out.println(question56.merge(intervas));
    }

    public List<Interval> merge(List<Interval> intervals) {
        //他妈的明明思路和最高votes一样的,复杂度确高不少,为啥?

        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Interval> res = new ArrayList<>();

        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;

        for (Interval interval : intervals) {
            if (start == Integer.MIN_VALUE) {
                start = interval.start;
                end = interval.end;
                continue;
            }

            if (interval.start <= end) {
                end = Math.max(end, interval.end);
                start = Math.min(start, interval.start);
            } else {
                Interval temp = new Interval(start, end);
                res.add(temp);
                start = interval.start;
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        //思路:先对左边界进行排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            if (newInterval[1] >= interval[0]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

}