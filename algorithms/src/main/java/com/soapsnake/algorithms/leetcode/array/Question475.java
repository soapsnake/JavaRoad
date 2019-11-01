package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-22 22:18
 */
public class Question475 {

    public static void main(String[] args) {
        Question475 question475 = new Question475();
        int[] hous = {1, 5};
        int[] hea = {2};
        question475.findRadius(hous, hea);
    }

    public int findRadius(int[] houses, int[] heaters) {

        //heater之间差值的最大值
        //最大heater与右边端点的差值
        //最小heater与左端点的差值
        //以上三个值找出最大的一个,如果是与端点差值最大那么半径就是该值,如果是heater之间的差值最大,那么该值的一半为半径

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0, res = 0;
        for (int house : houses) {
            while (i < heaters.length - 1 && heaters[i] + heaters[i + 1] <= house * 2) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }
}
