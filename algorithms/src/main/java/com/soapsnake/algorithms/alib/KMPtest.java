package com.soapsnake.algorithms.alib;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KMPtest {

	public static boolean kmpMatch(String s, String p) {
		//找出s中是否有完全匹配p的字串
		/**
		 *  abcdab:
		 *  a  0
		 *  ab: b,a
		 *  abc: a,ab, c,bc      0
		 *  abcd: a,ab,abc, d, cd, bcd      0
		 *  abcda: a, ab, abc, abcd, a, da, cda, bcda   1(a有重复)
		 *  abcdab: a, ab, abc, abcd, abcda, b, ab, dab, cdab, bcdab  1 (ab重复)
		 *  [0,0,0,0,1,1]
		 *
		 *  构造next池
		 */
		int[] nextIntkey = genNext(p);
		//should be[0,0,0,0,1,2,0]
		System.out.println(Arrays.toString(nextIntkey));

		boolean res = false;
		int i = 0, j = 0;
		while (i < s.length() && j < p.length()) {
			if (nextIntkey[j] == -1 || s.charAt(i) == p.charAt(j)) {
				i++;
				j++;
			} else {
				j = nextIntkey[j];
			}
			if (j == p.length() - 1) {
				System.out.println("匹配时j = " + j + " i = " + i);
				res = true;   //已经找到匹配了,直接退出吧
				break;
			}
		}
		System.out.println(s.substring(i + 1, i + p.length() + 1));
		return res;
	}

	private static int[] genNext(String p) {
		//这个求next[]的过程其实也是一个小型的字符串匹配过程,只不过我们知道p和它自己肯定是匹配的
		int[] next = new int[p.length()];
		int j = 0;
		int k = -1;
		int len = p.length();
		next[0] = -1;   //这个第0个元素为-1其实很关键
		while (j < len - 1) {
			if (k == -1 || p.charAt(k) == p.charAt(j)) {
				j++;
				k++;
				next[j] = k;
			} else {
				// 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
				// p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
				k = next[k];
			}
		}
		return next;
	}

	private static int[] genNext2(String p) {
		int[] nextIntkey = new int[p.length()];
		for (int i = 0; i < p.length(); i++) {
			String sub = p.substring(0, i + 1);
			Set<String> set = new HashSet<>();
			//部分匹配表是针对sub来构建的
			int key = 0;
			for (int j = 0; j < i; j++) {
				String prestr = sub.substring(0, j + 1);
				System.out.println("prestr = " + prestr);
				if (!set.add(prestr)) {
					System.out.println("重复元素 =" + prestr);
					key = prestr.length();
				}
				String postStr = sub.substring(j + 1, i + 1);
				System.out.println("postStr = " + postStr);
				if (!set.add(postStr)) {
					System.out.println("重复元素 =" + postStr);
					key = postStr.length();
				}
			}
			System.out.println("sub = " + sub + " 结束!");
			nextIntkey[i] = key;
		}
		return nextIntkey;
	}

	public static void main(String[] args) {
		String s = "BBC ABCDAB ABCDABCDABDE";
		String p = "ABCDABD";
		System.out.println(kmpMatch(s, p));
	}

}
