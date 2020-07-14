package com.soapsnake.algorithms.leetcode.number;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-14
 */
public class Question1344 {

    //leetcode1344
    public double angleClock(int hour, int minutes) {
        //表盘有360度,一共12个小时,一个小时就是30度,分钟数化成小时然后乘以30
        double h = (hour % 12 * 30) + ((double) minutes / 60 * 30);

        // Degree covered by minute hand (Each minute = 6 degree)
        double m = minutes * 6;

        //计算时针和分针的夹角
        double angle = Math.abs(m - h);

        //因为题目要求取锐角,所以这里处理一下
        if (angle > 180)
            angle = 360.0 - angle;

        return angle;
    }
}
