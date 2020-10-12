package com.soapsnake.algorithms.leetcode.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-19 09:59
 */
public class Question36 {

    /**
     * 1. 每一大行不能重复
     * 2. 每一大列不能重复
     * 3. 每一个9宫格不能重复(校验这个最难)
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {               //1. check row by row
                if (board[i][j] == '.') {
                    continue;
                }
                if (set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < board.length; i++) {                 //2. check column by column
                if (board[i][j] == '.') {
                    continue;
                }
                if (set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
        }
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {                             //3. check each 3*3 subbox
                int m = i / 3 * 3 + j / 3;
                int n = i % 3 * 3 + j % 3;    //这题最关键的就是这个m和n的值得求法
                if (board[m][n] == '.') {
                    continue;
                }
                if (set.contains(board[m][n])) {
                    return false;
                }
                set.add(board[m][n]);
            }
        }
        return true;
    }
}
