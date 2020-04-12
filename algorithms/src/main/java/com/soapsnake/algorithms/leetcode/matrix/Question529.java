package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-04-10
 */
public class Question529 {

    /**
     * 1. 如果点击的是一个M,说明点击到了炸弹,游戏结束退出
     * 2. 如果点击到的是一个空的格子,取决于周围的矿数分以下两种情况
     *      2.1如果周围有矿,在格子中标记周围的矿 数,完结退出
     *      2.2如果周围没有矿,格子标记B,然后继续搜索其周围的8个邻居格子
     */
    //leetcode529
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        //如果被点击的格子是💣,那么游戏失败退出
        if (board[row][col] == 'M') { // Mine
            board[row][col] = 'X';
        }
        else { // Empty
            // Get number of mines first.
            int count = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    int r = row + i, c = col + j;
                    if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                }
            }

            if (count > 0) { // If it is not a 'B', stop further DFS.
                board[row][col] = (char)(count + '0');
            }
            else { // Continue DFS to adjacent cells.
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[] {r, c});
                    }
                }
            }
        }
        return board;
    }
}
