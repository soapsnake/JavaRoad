package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-22
 */
public class Question419 {

    //leetcode419
    public int countBattleships(char[][] board) {
        int m = board.length;
        if (m==0) return 0;
        int n = board[0].length;
        int count=0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.') continue;
                if (i > 0 && board[i-1][j] == 'X') continue;
                if (j > 0 && board[i][j-1] == 'X') continue;
                count++;
            }
        }
        return count;
    }
}
