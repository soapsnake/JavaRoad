package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-24 21:10
 */
public class Question8 {

    public static void main(String[] args) {
        Question8 question8 = new Question8();
        String str = "   -42";
        System.out.println(question8.myAtoi(str));
    }

    public int myAtoi(String str) {
        double result = 0;
        int POrN = 1;
        int count = 0;
        char[] charArr = str.toCharArray();
        for(char c:charArr){
            count ++;
            if( c >='0' && c <='9' ){
                result *= 10;
                result += ( c - '0');
            }else if( c == '-' && count == 1){
                POrN = -1;
            }else if( c == '+' && count == 1){
                POrN =  1;
            }else if( c == ' ' && count == 1){
                count --;
            }else{
                break;
            }

        }
        if( result > Integer.MAX_VALUE ){
            if(POrN == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }else{
            return (int)(result * POrN);
        }
    }

}
