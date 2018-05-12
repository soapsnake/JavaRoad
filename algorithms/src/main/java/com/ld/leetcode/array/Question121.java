package com.ld.leetcode.array;


/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 design an algorithm to find the maximum profit.
 中文翻译:找出数组中,后面的数字减去前面的数字的最大值,如果后面的数字都比前面的小,那么返回0
 */
public class Question121 {
    //n平方级别算法
    public int maxProfit(int[] prices) {
        int max = 0;
        if (null == prices){
            return max;
        }
        for (int i=0;i<prices.length;i++){
            for (int j=i+1;j<prices.length;j++){
                if (prices[j] - prices[i] > max){
                    max = prices[j] - prices[i] ;
                }
            }
        }
        return max;
    }

    //稍微改了下算法,复杂度下降到o(n)级别
    public int maxProfit2(int[] prices) {
        int max = 0;
        if (null == prices){
            return max;
        }
        for (int i=0;i<prices.length;i++){
            for (int j = prices.length-1;j>i;j--){
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
                if (i > j){
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Question121 question121 = new Question121();
        int[] pr = new int[]{7,1,5,3,6,4};

        int[] pr2 = new int[]{7,6,4,3,1};
//        System.out.println(question121.maxProfit(pr));

        System.out.println(question121.maxProfit2(pr2));
    }

}
