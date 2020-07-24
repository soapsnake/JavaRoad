package com.soapsnake.algorithms.leetcode.tree.normaltree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question51 {

    /**
     * n皇后问题
     * @param n 棋盘格数
     * @return 合理的组合:规则为任何一个棋子的八个方向不能出现其他棋子
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if(colIndex == board.length) {
            res.add(construct(board));
            return;
        }

        for(int i = 0; i < board.length; i++) {
            if(validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
//                System.out.println("j = " + j + " -> y = " + y);

                //这个判断是整个算法的核心部分
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        Question51 question51 = new Question51();
        System.out.println(question51.solveNQueens(10));
    }
}
