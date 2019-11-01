package com.soapsnake.algorithms.alib;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysTester {

			//[1,1,2,2,3,4,4,5,5,5] 找出不重复的元素（黄包车）

	public static int findNotDup2(int[] arr) {
		if (arr == null)
			return 0;
		if (arr.length == 1) {
			return arr[0];
		}
		Arrays.sort(arr);
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1]) {
				return arr[i];
			}
		}
		return -1;
	}

		public static int findNotDup(int[] arr) {
		if (arr == null)
			return 0;
		if (arr.length == 1) {
			return arr[0];
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.get(arr[i]) == null ? 0 : map.get(arr[i]));
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getKey() == 1) {
				return entry.getKey();
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {1,1,2,2,3,4,4,5,5,5};
		System.out.println(findNotDup2(arr));
	}

	public boolean matrixBinerSearch(int[][] matrix, int target) {
		if (matrix == null) {
			return false;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;

		int begin = 0;
		int end = rows * cols - 1;
		while (end >= begin) {
			int mid = begin + (end - begin) / 2;
			int midTar = matrix[mid / cols][mid % cols];
			if (midTar == target) {
				return true;
			} else if (midTar > target) {
				end = mid - 1;
			} else {
				begin = mid + 1;
			}
		}
		return false;
	}

	@Test
	public void testMatrixBinerSearch() {
		int[][] matxi = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
		int tar = 100;
		System.out.println(matrixBinerSearch(matxi, tar));
	}

	public void fastSort(int[] ints) {
		if (ints.length == 0) {
			return;
		}
		int left = 0;
		int right = ints.length - 1;
		fastSort(ints, left, right);
	}

	private void fastSort(int[] ints, int left, int right) {
		if (ints == null || ints.length <= 1 || right <= left){
			return;
		}
		int i = left;
		int j = right;
		int mid = left + (right - left) / 2;
		while (j >= i) {
			while (ints[i] < ints[mid]) {
				++i;
			}
			while (ints[j] > ints[mid]) {
				--j;
			}
			if (j > i) {
				int temp = ints[i];
				ints[i] = ints[j];
				ints[j] = temp;
				++i;
				--j;
			} else if (j == i) {
				++i;
			}
		}
		fastSort(ints,left, j);
		fastSort(ints, i, right);
	}

	@Test
	public void testfastSort() {
		int[] dest = {1,23,4, 78, 24, 23,12, 8, 19, 7, 5};
		fastSort(dest);
		System.out.println(Arrays.toString(dest));
	}
}
