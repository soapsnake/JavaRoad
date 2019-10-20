package com.soapsnake.algorithms.alib;

import java.lang.annotation.Target;

public class IntUtils {

	public static int strToInt(String str) {
		if (str == null || "".equals(str)) {
			return 0;
		}
		boolean positive = true;
		int i = 0;
		int len = str.length();
		long cal = 0;
		while (i < len) {
			if (i == 0) {
				if (str.charAt(i) == '+') {

				} else if (str.charAt(i) == '-') {
					positive = false;
				} else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
					return 0;
				} else {
					cal = str.charAt(i) - '0';
				}
			} else {
				if (str.charAt(i) < '0' || str.charAt(i) > '9') {
					return 0;
				}
				cal = cal * 10 + (str.charAt(i) - '0');
				if (cal > Integer.MAX_VALUE) {
					if (positive) {
						return Integer.MAX_VALUE;
					} else {
						return Integer.MIN_VALUE;
					}
				}
			}
			i++;
		}
		if (positive) {
			return (int) cal;
		} else {
			return (int)-cal;
		}
	}




	public static int revertNum(int x) {
		int result = 0;
		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
//			if ((newResult - tail) / 10 != result) {
//				return 0;
//			}
			result = newResult;
			x = x / 10;
		}
		return result;
	}

	//求字符串“efabcbaefehiabcba”中最长的回文数，不去重（美团）

	int maxLen = 0;
	int left = 0;
	int right = 0;
	public  String findMaxSub(String string) {
		if (string == null || string.equals("")) {
			return string;
		}
		int len = string.length();
		for (int i = 0; i < len; i++) {
			extendStr(string, i, i);
			extendStr(string, i, i + 1);
		}
		return string.substring(left, right + 1);
	}

	private void extendStr(String string, int i, int j) {
		if (i < 0 || j >= string.length()) {
			return;
		}
		if (string.charAt(i) == string.charAt(j)) {
			if ((j - i) > maxLen) {
				maxLen = j -i;
				left = i;
				right = j;
			}
			i--;
			j++;
			extendStr(string, i, j);
		}
	}

	private void extendStrWhile(String string, int i, int j) {
		while (i >0 && j < string.length() - 1 && string.charAt(i) == string.charAt(j)) {
			i--;
			j++;
		}
		if (j - i > maxLen) {
			maxLen = j - i;
			left = i;
			right = j;
		}
	}


	public static void main(String[] args) {
//		System.out.println(strToInt("1983498290483921043"));

//		System.out.println(revertNum(1));

		IntUtils utils = new IntUtils();
//		System.out.println(utils.findMaxSub("babad"));
		int[] arr = {0,2,2,3,5,6,7,9,10,11,13,14,18};
		System.out.println(binerSearchIter(arr, 100));
	}

	public static int binerSearchIter(int[] arr, int tar) {
		if (arr.length == 0) {
			return -1;
		}
//		return binerSearchIter(arr, 0, arr.length - 1, tar);
		return binerSearchWhile(arr,tar);
	}

	private static int binerSearchWhile(int[] arr, int tar) {
		int left = 0;
		int right = arr.length - 1;
		while (right >= left) {
			int midIndex = left + (right - left) / 2;
			if (arr[midIndex]  == tar) {
				return midIndex;
			} else if (arr[midIndex] > tar) {
				right = midIndex - 1;
			} else {
				left = midIndex + 1;
			}
		}
		return - 1;
	}


	private static int binerSearchIter(int[] arr, int left, int right, int tar) {
		if (left > right) {
			return -1;
		}
		int midIndex = left + (right - left) / 2;
		if (arr[midIndex] == tar) {
			return midIndex;
		} else if (arr[midIndex] > tar) {
			return binerSearchIter(arr, left, midIndex - 1, tar);
		} else {
			return binerSearchIter(arr, midIndex + 1, right, tar);
		}
	}
}
