package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import lombok.ToString;
import sun.util.resources.hr.CalendarData_hr;

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

    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        if (board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
                //与边界相连的0不能转为x,先把这些染成'#',然后再遍历一次把非'#'的全部改成'0'
            if (board[0][i] == 'O') {
                this.helper(board, 0, i);
            }
            if (board[n - 1][i] == 'O') {
                this.helper(board, 3, i);
            }
        }
        for (int i = 0; i < m; i++) {
            //与边界相连的0不能转为x,先把这些染成'#',然后再遍历一次把非'#'的全部改成'0'
            if (board[i][0] == 'O') {
                this.helper(board, i, 0);
            }
            if (board[i][m - 1] == 'O') {
                this.helper(board, i, 3);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void helper(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i >= m || i < 0 || j >= n || j < 0) {
            return;
        }
        if (board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        //能够往目标格子移动有一个前提:目标格子必须也是'0'
        helper(board, i, j + 1);
        helper(board, i, j - 1);
        helper(board, i + 1, j);
        helper(board, i - 1, j);
    }


    /**
     * [["X","X","X","X"],
     * ["X","X","X","X"],
     * ["X","X","X","X"],
     * ["X","O","X","X"]]
     */
    @Test
    public void testSolve() {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println("12342432");
        System.out.println(Arrays.deepToString(board));
    }

    public boolean exist(char[][] board, String word) {
        //求单词word是否在board中存在
        Trie head = new Trie();
        Trie root = head;
        char[] chars= word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1) {
                root.children[chars[i] - 'A'] = new Trie();
                root = root.children[chars[i] - 'A'];
            } else {
                root.word = word;
            }
        }
        System.out.println(head);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    return this.dfs(board, i, j, head, word);
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, Trie root, String word) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
//        System.out.println("i = " + i + " j = " + j + " board.length = " + board.length +
//                " board[0].len = " + board[0].length) ;
        char cur = board[i][j];
        if (root.children[cur - 'A'] == null) {
            //叶子了
            System.out.println("herere  word = " + root.word + " ");
            return word.equals(root.word);
        }
        //非叶子
        Trie next = root.children[cur - 'A'];
        dfs(board, i + 1, j, next, word);
        dfs(board, i - 1, j, next, word);
        dfs(board, i, j + 1, next, word);
        dfs(board, i, j - 1, next, word);
        return true;
    }

    @ToString
    class Trie {
        String word;
        Trie[] children;
        public Trie() {
            this.children = new Trie[26];
        }
    }

    @Test
    public void testTire1() {
        char[][] boards = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(boards, word));
    }

    //会议室算法,count是不重叠的会议数量,但是这个题要的是重叠数量
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        System.out.println(Arrays.deepToString(intervals));
        int k = 0;
        int count = 1; //合法数量
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[k][1]) {
                //到这里说明这个是不合法的
                k = i;
                count++;
            }
        }
        return intervals.length - count;
    }

    @Test
    public void testEraseOverlatp() {
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public int solution2(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0) {
            return 0;
        }
        int countHead = 0;
        int countTail = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                countHead++;
            } else {
                countTail++;
            }
        }
        int res = Math.abs(countHead - countTail) / 2;
        return res;
    }

    public int solution11(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0) {
            return -2;
        }
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int val = A[i];
            map.putIfAbsent(val, new PriorityQueue<>());
            map.get(val).add(i);
        }
        Iterator<PriorityQueue<Integer>> keyItr = map.values().iterator();
        PriorityQueue<Integer> queue0 = keyItr.next();
        int[] preVals = new int[queue0.size()];
        for (int i = 0; i < queue0.size(); i++) {
            preVals[i] = queue0.poll();
        }

        int result = Integer.MAX_VALUE;
        while (keyItr.hasNext()) {
            PriorityQueue<Integer> current = keyItr.next();
            int[] currVals = new int[current.size()];
            for (int i = 0; i < current.size(); i++) {
                currVals[i] = current.poll();
            }

            int min = Integer.MAX_VALUE;
            int a = 0;
            int b = 0;
            while (a < preVals.length && b < currVals.length) {
                int val0 = preVals[a];
                int val1 = currVals[b];
                min = Math.min(min, Math.abs(val1 - val0));
                if (val1 > val0) {
                    a++;
                } else {
                    b++;
                }
            }
            result = Math.min(result, min);
            preVals = currVals;
        }
        return result;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int min = Integer.MAX_VALUE;
        for (int p = 0; p < A.length; p++) {
            for (int q = p + 1; q < A.length; q++) {
                boolean isAdjacent = true;
                for (int i = 0; i < A.length; i++) {
                    if ((A[i] > A[p] && A[i] < A[q]) || (A[i] < A[p] && A[i] > A[q])) {
                        isAdjacent = false;
                        break;
                    }
                }
                if (isAdjacent) {
                   min = Math.min(min, Math.abs(A[p] - A[q]));
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -2;
        }
        if (min > 100000000) {
            return -1;
        }
        return min;
    }

    public int solution1(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        //经过排序后,所有的value单调递增,相邻的两个数字必定是adj的,那么这道题事实上变成了求相邻两个数字的最小差值.
        //那么再遍历一次即可,复杂度下降到nlog(n)
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            min = Math.min(min, Math.abs(A[i] - A[i + 1]));
        }
        return min;
    }

    @Test
    public void testSoluc() {
        System.out.println(solution1(new int[]{0, 3, 3, 7, 5, 3, 11, 1}));
    }


    public int solution123(String[] B) {
        // write your code in Java SE 8
        if (B == null || B.length == 0) {
            return 0;
        }
        int size = B.length;
        char[][] board = new char[size][size];
        for (int i = 0; i < board.length; i++) {
            board[i] = B[i].toCharArray();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                   this.chessHelper(board, i, j);
                }
            }
        }
        return res;
    }
    int res;
    private void chessHelper(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i >= m || i < 0 || j >= n || j < 0) {
            return;
        }
        if (board[i][j] == 'X') {
            return;
        }
        if (board[i][j] == '.') {
            res++;
        }
        //右上角
        if (i - 1 > 0 && j + 1 < n && board[i - 1][j + 1] == 'X') {
            chessHelper(board, i - 2, j + 2);
        }
        //左上
        if (i - 1 >0 && j - 1 > 0 && board[i - 1][j - 1] == 'X') {
            chessHelper(board, i - 2, j - 2);
        }
    }
    @Test
    public void testdfs() {
        System.out.println(solution123(new String[]{"..X...", "......", "....X.", ".X....", "..X.X.", "...O.."}));
    }

    @Test
    public void testOuter() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("123", "456", "789"));
        lists.add(Arrays.asList("432", "dfs", "32131"));
        lists.add(Arrays.asList("ffdafs", "fdafds", "fdsafdsa"));
        outer:
        for (List<String> list : lists) {
            System.out.println("now will iterate list :" + list);
            for (String string : list) {
                System.out.println("now find string is: " + string);
                if (string.equalsIgnoreCase("dfs")) {
                    //如果一个list含有dfs,则整个list都会被跳过而不是仅仅只跳过这一个字符串
                    System.out.println("find dfs,will jump off this list: " + list);
                    continue outer;
                }
            }
        }
    }

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> cur = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 2; i <= N; ++i) {
            List<Integer> cur2 = new ArrayList<>();
            for (int x : cur) {
                int y = x % 10;
                if (x > 0 && y + K < 10)
                    cur2.add(x * 10 + y + K);
                if (x > 0 && K > 0 && y - K >= 0)
                    cur2.add(x * 10 + y - K);
            }
            cur = cur2;
        }
        return cur.stream().mapToInt(j->j).toArray();
    }

    private void backTrace(int preNumber, int N, int K, StringBuilder res, List<String> total) {
        //满足条件时的策略
        res.append(preNumber);
        if (res.length() == N) {
            total.add(res.toString());
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (preNumber + K < 10) {
                int cur = preNumber + K;
                res.append(cur);
                backTrace(cur, N, K, res, total);
                res.deleteCharAt(res.length() - 1);
            }
            if (preNumber - K > 0) {
                int cur = preNumber - K;
                res.append(cur);
                backTrace(cur, N, K, res, total);
                res.deleteCharAt(res.length() - 1);
            }
        }
    }
}
