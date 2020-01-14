package com.soapsnake.algorithms.leetcode.number;

import java.sql.SQLOutput;

public class Question365 {

    //z升的水能不能用x和y量出来
    //贝祖定理:  对于等式 ax + by = d  如果d是x与y的最大公约数,则a和b一定存在
    //12升和3升,量9升,实际上只是用3升的倒三次就可以量出9升了
    public boolean canMeasureWater(int x, int y, int z) {   //12, 3 , 9
        //limit brought by the statement that water is finallly in one or both buckets
        if (x + y < z)
            return false;
        //case x or y is zero
        if (x == z || y == z || x + y == z)
            return true;

        //get GCD, then we can use the property of Bézout's identity
        return z % GCD(x, y) == 0;   //9 % 3 == 0
    }

    public int GCD(int a, int b) {  //12, 3
        while (b != 0) {
            int temp = b;  //3
            b = a % b;     //b = 12 % 3 = 0
            a = temp;      //a = 3
        }
        return a; //3
    }

    public static void main(String[] args) {
        System.out.println(10 % 2);
        Question365 question365 = new Question365();
        System.out.println(question365.canMeasureWater(12, 3, 9));
    }
}
