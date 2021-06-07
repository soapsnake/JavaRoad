package com.soapsnake.algorithms.leetcode.greedy;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import com.soapsnake.algorithms.alib.Person;

/**
 * 
 * Created on 2020-10-11
 */
public class Question452 {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new myComparator());
        int count = 0;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                count += 1;
            } else {
                end = points[i][1];
            }
        }
        return points.length - count;
    }

    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        }
    }

    @Test
    public void testFindMinArrowShots() {
//        Assert.fail("should this logic be false");
        Person person1 = new Person("liudun", 18, 180);
        Person person2 = new Person("liudun", 18, 180);
        Assert.assertEquals(person1, person2);



    }
}
