package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question252 {

    public static void main(String[] args) {
        Question252 question252 = new Question252();
        Pair pair1 = new Pair(0, 10);
        Pair pair2 = new Pair(15, 20);
        Pair pair3 = new Pair(30, 50);
        List<Pair> list = Arrays.asList(pair1, pair2, pair3);
        System.out.println(question252.canAttende(list));
    }

    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.
     * <p>
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return false.
     */
    public boolean canAttende(List<Pair> pairs) {
        pairs.sort(Comparator.comparingInt(o -> o.start));
        for (int i = 1; i < pairs.size(); i++) {
            if (pairs.get(i).start < pairs.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }

    static class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


}
