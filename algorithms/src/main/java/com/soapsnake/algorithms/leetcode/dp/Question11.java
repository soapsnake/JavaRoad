package com.soapsnake.algorithms.leetcode.dp;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-06 13:37
 */
public class Question11 {

    /**
     * Input: [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * <p>
     * (长方形长度)索引差尽可能大,长方形高度已低的为准.
     */
    public int maxArea(int[] height) {
        //不能排序,感觉是dp问题
        if (height.length < 2) {
            return height[0];
        }
        int max = -1;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int gao = Math.min(height[i], height[j]);
                max = Math.max(max, gao * (j - i));
            }
        }
        return max;
    }

    /**
     * 动态规划,看不懂
     */
    //leetcode11
    public int maxArea2(int[] height) {
        int area = 0, i = 0, j = height.length - 1;

        while (i < j) {   //左右指针碰撞
            int width = j - i;
            int high = height[i] <= height[j] ? height[i++] : height[j--];  //谁小取谁谁移动
            int currArea = width * high;

            if (currArea > area)
                area = currArea;
        }

        return area;
    }


    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        int third = first + second;
        for (int i = 1; i < n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    public static int tribonacci(int n) {
        if (n <= 2) {
            return n;
        }
        int first = 0;
        int second = 1;
        int third = first + second;
        int fourth = first + second + third;
        for (int i = 1; i < n; i++) {
            fourth = first + second + third;
            first = second;
            second = third;
            third = fourth;
        }
        return fourth;
    }

    public static void main(String[] args) {
        System.out.println(fib(4));
    }
}
