package com.soapsnake.algorithms.leetcode.tree.normaltree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question52 {

    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        this.dfs(board, res, 0);
        return res.size();
    }

    private void dfs(char[][] board, List<List<String>> res, int colIndex) {
        if (colIndex == board.length) {
            res.add(this.buildBoard(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (this.validFlag(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, res, colIndex + 1);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validFlag(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'Q' &&
                        (x + j == y + i || x + y == i + j || x == i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> buildBoard(char[][] board) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}
