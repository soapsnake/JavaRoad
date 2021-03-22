package com.soapsnake.algorithms.leetcode.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2020-05-13
 */
public class Question593 {

	//判断输入的四个坐标能不能构成一个正方形
	//Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
	//leetcode593
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		//思路:计算所有顶点之间的距离,只要非最长距离(对角线)之间的长度一致就可以了,菱形???
		int[] dis = new int[]{distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3)
				, distance(p2, p4), distance(p3, p4)};
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		for (int i : dis) {
			max = Math.max(max, i);
			if (!map.containsKey(i)) {
				map.put(i, 1);
			} else {
				map.put(i, map.get(i) + 1);
			}
		}
		return map.get(max) == 2 && map.size() == 2;
	}

	public int distance(int[] p1, int[] p2) {
		return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		boolean[] used = new boolean[nums.length];
		this.backTrace(nums, res, new ArrayList<>(), used);
		return res;
	}

	private void backTrace(int[] nums, List<List<Integer>> res, List<Integer> tmp, boolean[] used) {
		if (tmp.size() == nums.length) {
			if (!res.contains(tmp)) {
				res.add(new ArrayList<>(tmp));
				return;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				continue;
			}
			used[i] = true;
			tmp.add(nums[i]);
			backTrace(nums, res, tmp, used);
			used[i] = false;
			tmp.remove(tmp.size() - 1);
		}
	}
}
