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
		System.out.println(strToInt("1983498290483921043"));
	}
}
