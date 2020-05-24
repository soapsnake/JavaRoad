package com.soapsnake.algorithms.alib;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixTester {

    /**
     * 题目:求一个矩阵中最大的二维矩阵(元素和最大).如:
     * 1 2 0 3 4
     * 2 3 4 5 1
     * 1 1 5 3 0
     * <p>
     * 中最大的是:
     * 4 5                     00, 01, 02, 03
     * 5 3                     10, 11, 12, 13
     */
    public int maxSubMatrix(int[][] matrix) {
        int rowlen = matrix.length;
        int collen = matrix[0].length;
        if (matrix == null || rowlen < 1 || collen < 1) {
            return 0;
        }
        int sum = 0;
        int[][] res = new int[2][2];
        for (int i = 1; i < rowlen; ++i) {
            for (int j = 1; j < collen; ++j) {
                int tmp = matrix[i - 1][j - 1] + matrix[i - 1][j] + matrix[i][j - 1] + matrix[i][j];
                if (tmp > sum) {
                    sum = tmp;
                    res[0][0] = matrix[i - 1][j - 1];
                    res[0][1] = matrix[i - 1][j];
                    res[1][0] = matrix[i][j - 1];
                    res[1][1] = matrix[i][j];
                    //如果题目要求返回最大的二维数组,那么返回这个res就可以了
                }
            }
        }
        return sum;
    }

    @Test
    public void testMaxSubMattix() {
        maxSubMatrix(null);
    }


    /**
     * n支队伍比赛，分别编号为0，1，2。。。。n-1，已知它们之间的实力对比关系，
     * 存储在一个二维数组w[n][n]中，w[i][j] 的值代表编号为i，j的队伍中更强的一支。
     * 所以w[i][j]=i 或者j，现在给出它们的出场顺序，并存储在数组order[n]中，
     * 比如order[n] = {4,3,5,8,1......}，那么第一轮比赛就是 4对3， 5对8。.......
     * 胜者晋级，败者淘汰，同一轮淘汰的所有队伍排名不再细分，即可以随便排，
     * 下一轮由上一轮的胜者按照顺序，再依次两两比，比如可能是4对5,直至出现第一名
     * 编程实现，给出二维数组w，一维数组order 和 用于输出比赛名次的数组result[n]，
     * 求出result。
     *
     * @return
     */
    public Integer[] countWinner(int[][] winlose, int[] order) {
        if (order == null || order.length == 0 || winlose == null || winlose.length == 0 || winlose[0].length == 0) {
            return null;
        }
        List<Integer> winner = Arrays.stream(order).boxed().collect(Collectors.toList());
        List<Integer> loser = new LinkedList<>();
        int j = 1;
        while (!winner.isEmpty() && winner.size() > 1) {
            List<Integer> tempLoser = new ArrayList<>();
            for (int i = 0; i < winner.size() - 1; i += 2) {
                int one = winner.get(i);
                int two = winner.get(i + 1);
                //两人中的赢家
                int win = winlose[one][two];
                int lose = one == win ? two : one;
                //这一轮的输家
                tempLoser.add(lose);
                System.out.println("第" + (j++) + "轮输家:" + lose);
            }
            if (!tempLoser.isEmpty()) {
                loser.addAll(tempLoser);
                //过滤掉已经输的选手
                winner.removeAll(tempLoser);
            }
        }
        if (winner.size() == 1) {
            //最后冠军
            loser.addAll(winner);
        }
        Collections.reverse(loser);
        return loser.toArray(new Integer[0]);
    }

    @Test
    /**
     *    0, 0, 0    //0赢1, 0赢2
     *    0, 1, 1    //0赢1, 1赢2
     *    0, 1, 2    //0赢2, 1赢2
     *    //1最强,2次之,0最弱
     */
    public void testCountWinner() {
        int[][] winlose = {{0, 0, 0}, {0, 1, 1}, {0, 1, 2}};
        int[] order = {0, 1, 2};
        System.out.println(Arrays.toString(countWinner(winlose, order)));
    }
}
