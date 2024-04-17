package com.soapsnake.algorithms.acwing.lecture1;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.totalNQueens(2));
    }

    private  int m;
    private  int count;
    public int totalNQueens(int n) {
        m = n;
        if(m == 1) return  1;
        dfs(0, new boolean[m], new boolean[2 * m], new boolean[2 * m]);
        return count;
    }

    private void dfs(int row, boolean[] column, boolean[] zhengxie, boolean[] fanxie) {
        if(row >= m) {
            this.count++;
        }

        for(int i = 0; i < m; i++ ) {
            if(!column[i] && !zhengxie[row + i] && !fanxie[row + m - i]) {
                column[i] = zhengxie[row + i] = fanxie[row + m - i] = true;
                dfs(row + 1, column, zhengxie, fanxie);
                column[i] = zhengxie[row + i] = fanxie[row + m - i] = false;
            }
        }
    }
}