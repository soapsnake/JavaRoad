package com.soapsnake.algorithms.leetcode.number;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-18
 */
public class Question405 {

    //leetcode405 10进制转16进制
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public String toHex(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result;
            num = (num >>> 4);
        }
        return result;
    }
}
