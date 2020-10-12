package com.soapsnake.algorithms.alib;


import java.util.Arrays;
/**
 */
public class Smart {
	public int solution(int[][] A) {
		// write your code in Java SE 8
		if (A == null || A.length == 0) {
			return 0;
		}
		int max = 0;
		int m = A.length;
		int n = A[0].length;
		flag = new int[m][n];
		map = A;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				//iterate every ele, find the target
				int res = find(A[i][j], i, j, 0);
				max += res;
			}
		}
		return max;
	}
	private int[][] flag;
	private int[][] map;
	private int find(int color, int x, int y, int num) {
		if (!isValid(color, x, y)){
			return num;
		}
		//valid ele we can go further
		num++;
		flag[x][y] = 1;
		find(color, x, y + 1, num);
		find(color, x + 1, y, num);
		find(color, x, y - 1, num);
		find(color, x - 1, y, num);
		return num;
	}

	private boolean isValid(int color, int x, int y) {
		int m = map.length;
		int n = map[0].length;
		return x >= 0 && x < m && y >= 0 && y < n && flag[x][y] == 0 && color == map[x][y];
	}

	public static void main(String[] args) {
		int[][] a = {{5, 4, 4}, {4, 3, 4}, {3, 2, 4}, {2, 2, 2}, {3, 3, 4}, {1, 4, 4}, {4, 1, 1}};
		Smart codeCmo = new Smart();
		System.out.println(Arrays.deepToString(a));
		System.out.println(codeCmo.solution(a));
	}


	public int solution2(int[] A) {
		// write your code in Java SE 8
		if (A == null || A.length == 0) {
			return 0;
		}
		if (A.length == 1) {
			return 1;
		}
		int max = 0;
		int index = 0;
		//brute force
		while (index < A.length) {
			int cur = index;
			int next = cur + 1;
			while (next + 2 < A.length && cur + 2 < A.length && A[cur] == A[cur + 2] && A[next] == A[next + 2]) {
				cur += 2;
				next += 2;
			}
			//in case if next beyond A.len, but cur is not
			if (cur + 2 < A.length && A[cur] == A[cur + 2]) {
				cur += 2;
			}
			//end here, we can get the size of this slice
			int end = Math.max(cur, next);
			max = Math.max(max, end - index + 1);
			index = end;
		}
		return max;
	}
}
