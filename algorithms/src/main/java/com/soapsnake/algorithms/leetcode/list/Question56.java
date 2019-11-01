package com.soapsnake.algorithms.leetcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-23 22:42
 */
public class Question56 {

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
}
