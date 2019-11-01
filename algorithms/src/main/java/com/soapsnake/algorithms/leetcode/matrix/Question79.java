package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 13:57
 */
public class Question79 {

    public int test = 1;
    private boolean[][] visited;  //关键是这个数组,防止走回头路

    public static void main(String[] args) {
        Question79 question79 = new Question79();
        System.out.println(question79.test);
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String str = "ABCCED";
        System.out.println(question79.exist(board, str));
        System.out.println(question79.test);


    }

    /**
     * Example:
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     */
    public boolean exist(char[][] board, String word) {
        int test = 2;
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((word.charAt(0) == board[i][j]) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        if (dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i, j - 1, index + 1) ||
                dfs(board, word, i, j + 1, index + 1)) {
            return true;
        }

        visited[i][j] = false;
        return false;
    }
}
