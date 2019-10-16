package com.soapsnake.algorithms.alib;

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


	public static void main(String[] args) {
//		System.out.println(strToInt("1983498290483921043"));

//		System.out.println(revertNum(1));

		IntUtils utils = new IntUtils();
		System.out.println(utils.findMaxSub("efabcbaefehiabcba"));
//		System.out.println(utils.findMaxSub("babad"));

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
}
