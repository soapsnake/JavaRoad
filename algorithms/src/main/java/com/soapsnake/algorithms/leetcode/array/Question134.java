package com.soapsnake.algorithms.leetcode.array;

/**

 * Created on 2020-01-02
 */
public class Question134 {

    /**
     * Example 1:
     *
     * Input:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     *
     * Output: 3
     *
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     *
     * If car starts at A and can not reach B. Any station between A and B
     * can not reach B.(B is the first station that A can not reach.)
     * If the total number of gas is bigger than the total number of cost. There must be a solution.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {   //小于0的情况,就是到下一站注定油不够用
                //不断的重置start,这里其实有瞎猜的意思,我并不验证start是不是结果,我只是觉得它是
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }
    }
}
