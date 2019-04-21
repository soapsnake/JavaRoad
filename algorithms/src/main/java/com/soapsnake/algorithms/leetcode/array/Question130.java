package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-21 15:43
 */
public class Question130 {

    public void solve(char[][] board) {

        //基本思路:已四边上的O作为起点,使用dfs算法找和他近邻的O节点,统一表示成*节点(相当于染色)
        //染色标记完后,再遍历一次地图,发现有剩余的O节点(还剩的O节点肯定就是围死的节点)统一改成X,*节点统一改回O,原来的X节点不变
        if ( board == null || board.length == 0) {
            return;
        }
        if (board.length < 2 || board[0].length < 2){
            return;
        }
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '*';   //与边界联通的O一律染成*
        }
        if (i > 1 && board[i - 1][j] == 'O') {  //如果左侧联通
            dfs(board, i - 1, j);            //那就往左走
        }
        if (i < board.length - 2 && board[i + 1][j] == 'O') { //如果右侧联通
            dfs(board, i + 1, j);           //那就往右走
        }
        if (j > 1 && board[i][j - 1] == 'O') {   //如果下方联通,那就往下(不会倒回去的吗???)
            dfs(board, i, j - 1);
        }
        if (j < board[i].length - 2 && board[i][j + 1] == 'O') { //如果上方联通,那就往上
            dfs(board, i, j + 1);
        }
    }
}
