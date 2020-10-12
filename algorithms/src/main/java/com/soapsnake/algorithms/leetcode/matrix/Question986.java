package com.soapsnake.algorithms.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created on 2020-05-23
 */
public class Question986 {

	//leetcode986
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		if(A == null || A.length == 0 || B == null || B.length == 0)
			return new int[][]{};
		List<int[]> res = new ArrayList<>();
		int i = 0, j = 0;
		int startMax, endMin;
		while(i < A.length && j < B.length){
			startMax = Math.max(A[i][0], B[j][0]);
			endMin = Math.min(A[i][1], B[j][1]);
			if(endMin >= startMax)
				res.add(new int[]{startMax, endMin});
			if(A[i][1] == endMin) i++;
			if(B[j][1] == endMin) j++;
		}
		return res.toArray(new int[res.size()][2]);
	}

	//找到数组A中未出现的第一个正整数
	public int solution(int[] A) {
		// write your code in Java SE 8
		Arrays.sort(A);
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == A[i + 1]) {
				continue;
			}
			if (A[i] + 1 != A[i + 1] && (A[i] + 1) > 0) {
				return A[i] + 1;
			}
		}
		if (A[A.length - 1] <= 0) {
			return 1;
		} else {
			return A[A.length - 1] + 1;
		}
	}
}
