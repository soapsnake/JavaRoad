package com.soapsnake.algorithms.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/17
 */
class Question812 {

    public double largestTriangleArea(int[][] points) {
        if (points.length <3) {
            return 0;
        }

        double maxX = 0;
        double maxY = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                maxX = Math.max(points[i][0], maxX);
                maxY = Math.max(points[i][1], maxY);
            }
        }





    return maxX * maxY * 0.5;
    }

    public static void main(String[] args) {
        Question812 question812 = new Question812();
        int[][] nums = {{0,0}, {0,1}, {1,0}, {0,2}, {2,0}};
        System.out.println(question812.largestTriangleArea(nums));
    }
}
