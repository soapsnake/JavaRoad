package com.soapsnake.algorithms.leetcode.stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */
class Question20 {

//    public static void main(String[] args) {
//        Question20 question20 = new Question20();
//        System.out.println(question20.isValid("()"));
//    }

    //利用栈来解决这个问题
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if ("".equals(s)) {
            return true;
        }

        for (Character c : s.toCharArray()) {
            if (c.equals('(')) {
                stack.push(')');
            } else if (c.equals('[')) {
                stack.push(']');
            } else if (c.equals('{')) {
                stack.push('}');
            } else if (stack.isEmpty() || !stack.pop().equals(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    char[][] g;
    int x;
    int y;
    public int numIslands(char[][] grid) {
        g = grid;
        x = g.length;
        if (x == 0) {
            return 0;
        }
        y = g[0].length;
        int count = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (g[i][j] == '1') {
                    this.dfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j) {
        if (i < 0 || j < 0 || i > x - 1 || j > y - 1 || g[i][j] == '0') {
            return;
        }
        g[i][j] = '0';
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
    }

    public static void main(String[] args) {
    }

}
