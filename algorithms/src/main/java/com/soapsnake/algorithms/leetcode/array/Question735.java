package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedList;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-21
 */
public class Question735 {

    //leetcode735
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>(); // use LinkedList to simulate stack so that we don't need to reverse at end.
        for (int i : asteroids) {
            while (!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                s.pollLast();
            if (s.isEmpty() || i > 0 || s.getLast() < 0)
                s.add(i);
            else if (i < 0 && s.getLast() == -i)
                s.pollLast();
        }
        return s.stream().mapToInt(i->i).toArray();
    }
}
