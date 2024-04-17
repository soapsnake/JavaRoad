package com.soapsnake.algorithms.acwing.lecture1;

import java.util.Arrays;

public class Asjdisjaidsa {

    private static char[][] board;
    private static int n;
    public static void main(String[] args) throws Exception {
        n = 4;
        board = new char[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        dfs(0, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }

    private static void dfs(int row, boolean[] column, boolean[] zhengxie, boolean[] fanxie) {
        if(row >= n) {
            for(int i = 0 ; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
        for(int i = 0; i < n; i++) {
            if(!column[i] && !zhengxie[row + i] && !fanxie[row + n - i]) {
                column[i] = zhengxie[row + i] = fanxie[row + n - i] = true;
                board[row][i] = 'Q';
                dfs(row + 1, column, zhengxie, fanxie);
                column[i] = zhengxie[row + i] = fanxie[row + n - i] = false;
                board[row][i] = '.';
            }
        }
    }

}
